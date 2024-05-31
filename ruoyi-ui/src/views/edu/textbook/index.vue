<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="教师" prop="teacherId">
        <el-select v-model="queryParams.teacherId" placeholder="请输入姓名" clearable size="small"
          filterable
          remote
          :remote-method="searchTeacher"
          :loading="loading">
          <el-option
            v-for="item in teacherOptions"
            :key="item.teacherId"
            :label="item.teacherName"
            :value="item.teacherId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="教材名称" prop="textbookName">
        <el-input
          v-model="queryParams.textbookName"
          placeholder="请输入教材名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="作者次序" prop="authorOrder">
        <el-select v-model="queryParams.authorOrder" placeholder="请选择作者次序" clearable size="small">
          <el-option
            v-for="dict in authorOrderOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="出版时间" prop="issueTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.issueTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择出版时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="出版社" prop="publisher">
        <el-input
          v-model="queryParams.publisher"
          placeholder="请输入出版社"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['edu:textbook:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['edu:textbook:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['edu:textbook:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['edu:textbook:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="textbookList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" align="center">
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="教师" align="center" prop="teacher.teacherName" />
      <el-table-column label="教材名称" align="center" prop="textbookName" />
      <el-table-column label="作者次序" align="center" prop="authorOrder">
        <template slot-scope="scope">
          <dict-tag :options="authorOrderOptions" :value="scope.row.authorOrder"/>
        </template>
      </el-table-column>
      <el-table-column label="出版时间" align="center" prop="issueTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.issueTime, '{y}-{m}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="出版社" align="center" prop="publisher" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['edu:textbook:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['edu:textbook:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改出版教材对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="教师" prop="teacherId">
          <el-select v-model="form.teacherId" placeholder="请输入姓名" clearable size="small"
            filterable
            remote
            :remote-method="searchTeacher"
            :loading="loading">
            <el-option
              v-for="item in teacherOptions"
              :key="item.teacherId"
              :label="item.teacherName"
              :value="item.teacherId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="教材名称" prop="textbookName">
          <el-input v-model="form.textbookName" placeholder="请输入教材名称" />
        </el-form-item>
        <el-form-item label="作者次序" prop="authorOrder">
          <el-select v-model="form.authorOrder" placeholder="请选择作者次序">
            <el-option
              v-for="dict in authorOrderOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="出版时间" prop="issueTime">
          <el-date-picker clearable size="small"
            v-model="form.issueTime"
            type="month"
            value-format="yyyy-MM"
            placeholder="选择出版时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="出版社" prop="publisher">
          <el-input v-model="form.publisher" placeholder="请输入出版社" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="附件" prop="fileUrl">
          <el-input v-model="form.fileUrl" placeholder="请输入附件" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listTextbook, getTextbook, delTextbook, addTextbook, updateTextbook, exportTextbook } from "@/api/edu/textbook";
import { listTeacher } from "@/api/edu/teacher";

export default {
  name: "Textbook",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 出版教材表格数据
      textbookList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 作者次序字典
      authorOrderOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        teacherId: null,
        textbookName: null,
        authorOrder: null,
        issueTime: null,
        publisher: null,
        fileUrl: null
      },
      // 教师选项
      teacherOptions: [],
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        teacherId: [
          { required: true, message: "教师不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("edu_author_order").then(response => {
      this.authorOrderOptions = response.data;
    });
  },
  methods: {
    /** 按姓名查询教师 */
    searchTeacher(query) {
      if (query !== '') {
        this.loading = true;
        setTimeout(() => {
          listTeacher({ teacherName: query }).then(response => {
            this.teacherOptions = response.rows.filter(item => {
              return item.teacherName.toLowerCase().indexOf(query.toLowerCase()) > -1;
            });
            this.loading = false;
          });
        }, 200);
      } else {
        this.teacherOptions = [];
      }
    },
    /** 查询出版教材列表 */
    getList() {
      this.loading = true;
      listTextbook(this.queryParams).then(response => {
        this.textbookList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        textbookId: null,
        teacherId: null,
        textbookName: null,
        authorOrder: null,
        issueTime: null,
        publisher: null,
        remark: null,
        fileUrl: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.textbookId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加出版教材";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const textbookId = row.textbookId || this.ids
      getTextbook(textbookId).then(response => {
        this.form = response.data;
        this.teacherOptions = [response.data.teacher];
        this.open = true;
        this.title = "修改出版教材";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.textbookId != null) {
            updateTextbook(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTextbook(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const textbookIds = row.textbookId || this.ids;
      this.$confirm('是否确认删除出版教材编号为"' + textbookIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delTextbook(textbookIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有出版教材数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportTextbook(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
