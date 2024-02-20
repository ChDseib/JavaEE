<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
    <el-form-item label="教师" prop="teacherId">
        <el-select
          v-model="queryParams.teacherId"
          placeholder="请输入姓名"
          clearable
          size="small"
          filterable
          remote
          :remote-method="searchTeacher"
          :loading="loading"
        >
          <el-option
            v-for="item in teacherOptions"
            :key="item.teacherId"
            :label="item.teacherName"
            :value="item.teacherId"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="类别" prop="category">
        <el-select
          v-model="queryParams.category"
          placeholder="请选择类别"
          clearable
          size="small"
        >
          <el-option
            v-for="dict in categoryOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
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
          v-hasPermi="['edu:parttime:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['edu:parttime:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['edu:parttime:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['edu:parttime:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="parttimeList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" align="center">
        <template slot-scope="scope">
          <span>{{
            (queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1
          }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="教师姓名"
        align="center"
        prop="teacher.teacherName"
      />
      <el-table-column label="类别" align="center" prop="category">
        <template slot-scope="scope">
          <dict-tag :options="categoryOptions" :value="scope.row.category" />
        </template>
      </el-table-column>
      <el-table-column label="兼职单位" align="center" prop="employer" />
      <el-table-column label="兼职职务" align="center" prop="position" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['edu:parttime:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['edu:parttime:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改社会兼职对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="老师" prop="teacherName">
          <el-select
            v-model="form.teacherId"
            placeholder="请输入姓名"
            filterable
            clearable
            remote
            :remote-method="searchTeacher"
            :loading="loading"
          >
            <el-option
              v-for="item in teacherOptions"
              :key="item.teacherId"
              :label="item.teacherName"
              :value="item.teacherId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="类别" prop="category">
          <el-select v-model="form.category" placeholder="请选择类别">
            <el-option
              v-for="dict in categoryOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="兼职单位" prop="employer">
          <el-input v-model="form.employer" placeholder="请输入兼职单位" />
        </el-form-item>
        <el-form-item label="兼职职务" prop="position">
          <el-input v-model="form.position" placeholder="请输入兼职职务" />
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
import {
  listParttime,
  getParttime,
  delParttime,
  addParttime,
  updateParttime,
  exportParttime,
} from "@/api/edu/parttime";
import { listTeacher } from "@/api/edu/teacher";

export default {
  name: "Parttime",
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
      // 社会兼职表格数据
      parttimeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 类别字典
      categoryOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        teacherId: null,
        category: null,
        employer: null,
        position: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
    };
  },
  created() {
    this.getList();
    this.getDicts("edu_parttime_category").then((response) => {
      this.categoryOptions = response.data;
    });
  },
  methods: {

   /** 按姓名查询教师 */
   searchTeacher(query) {
      if (query !== "") {
        this.loading = true;
        setTimeout(() => {
          listTeacher({ teacherName: query }).then((response) => {
            this.teacherOptions = response.rows.filter((item) => {
              return (
                item.teacherName.toLowerCase().indexOf(query.toLowerCase()) > -1
              );
            });
            this.loading = false;
          });
        }, 200);
      } else {
        this.teacherOptions = [];
      }
    },
    /** 查询社会兼职列表 */
    getList() {
      this.loading = true;
      listParttime(this.queryParams).then((response) => {
        this.parttimeList = response.rows;
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
        parttimeId: null,
        teacherId: null,
        category: null,
        employer: null,
        position: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
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
      this.ids = selection.map((item) => item.parttimeId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加社会兼职";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const parttimeId = row.parttimeId || this.ids;
      getParttime(parttimeId).then((response) => {
        this.form = response.data;
        this.teacherOptions = [response.data.teacher];
        this.open = true;
        this.title = "修改社会兼职";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.parttimeId != null) {
            updateParttime(this.form).then((response) => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addParttime(this.form).then((response) => {
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
      const parttimeIds = row.parttimeId || this.ids;
      this.$confirm(
        '是否确认删除社会兼职编号为"' + parttimeIds + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return delParttime(parttimeIds);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm("是否确认导出所有社会兼职数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.exportLoading = true;
          return exportParttime(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
          this.exportLoading = false;
        })
        .catch(() => {});
    },
  },
};
</script>
