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
      <el-form-item label="奖项" prop="honorName">
        <el-input
          v-model="queryParams.honorName"
          placeholder="请输入奖项"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="级别" prop="level">
        <el-select v-model="queryParams.level" placeholder="请选择级别" clearable size="small">
          <el-option
            v-for="dict in levelOptions"
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
          v-hasPermi="['edu:honor:add']"
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
          v-hasPermi="['edu:honor:edit']"
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
          v-hasPermi="['edu:honor:remove']"
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
          v-hasPermi="['edu:honor:export']"
          >导出</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-paperclip"
          size="mini"
          :loading="exportLoading"
          @click="handleDownload"
          v-hasPermi="['edu:award:export']"
          >下载附件</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="honorList"
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
      <el-table-column label="奖项" align="center" prop="honorName" />
      <el-table-column label="级别" align="center" prop="level">
        <template slot-scope="scope">
          <dict-tag :options="levelOptions" :value="scope.row.level" />
        </template>
      </el-table-column>
      <el-table-column
        label="获奖时间"
        align="center"
        prop="issueTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.issueTime, "{y}-{m}") }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="附件" align="center" prop="fileUrl" /> -->
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
            v-hasPermi="['edu:honor:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['edu:honor:remove']"
            >删除</el-button
          >
          <el-button
            type="text"
            size="small"
            icon="el-icon-download"
            @click="handleDownload(scope.row)"
            v-hasPermi="['edu:award:export']"
            >下载附件</el-button
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

    <!-- 添加或修改教师奖项对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="老师" prop="teacherId">
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
        <el-form-item label="奖项" prop="honorName">
          <el-input v-model="form.honorName" placeholder="请输入奖项" />
        </el-form-item>
        <el-form-item label="等级" prop="level">
          <el-select v-model="form.level" placeholder="请选择等级">
            <el-option
              v-for="dict in levelOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="获奖时间" prop="issueTime">
          <el-date-picker
            clearable
            size="small"
            v-model="form.issueTime"
            type="month"
            value-format="yyyy-MM"
            placeholder="选择获奖时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="附件">
          <fileUpload v-model="form.fileUrl" />
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
  listHonor,
  getHonor,
  delHonor,
  addHonor,
  updateHonor,
  exportHonor,
} from "@/api/edu/honor";
import { listTeacher } from "@/api/edu/teacher";

import { downLoadZip } from "@/utils/zipdownload";

export default {
  name: "Honor",
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
      // 教师奖项表格数据
      honorList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 级别字典
      levelOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        teacherId: null,
        honorName: null,
        level: null,
        issueTime: null,
        fileUrl: null,
      },
      // 教师选项
      teacherOptions: [],
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
    };
  },
  created() {
    this.getList();
    this.getDicts("edu_contest_level").then((response) => {
      this.levelOptions = response.data;
      T;
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

    /** 查询教师奖项列表 */
    getList() {
      this.loading = true;
      listHonor(this.queryParams).then((response) => {
        this.honorList = response.rows;
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
        honorId: null,
        teacherId: null,
        honorName: null,
        level: null,
        issueTime: null,
        fileUrl: null,
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
      this.ids = selection.map((item) => item.honorId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加教师奖项";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const honorId = row.honorId || this.ids;
      getHonor(honorId).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改教师奖项";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.honorId != null) {
            updateHonor(this.form).then((response) => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addHonor(this.form).then((response) => {
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
      const honorIds = row.honorId || this.ids;
      this.$confirm(
        '是否确认删除教师奖项编号为"' + honorIds + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return delHonor(honorIds);
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
      this.$confirm("是否确认导出所有教师奖项数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.exportLoading = true;
          return exportHonor(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
          this.exportLoading = false;
        })
        .catch(() => {});
    },
    /** 下载附件操作 */
    handleDownload(row) {
      const honorIds = row.honorId || this.ids;
      if (honorIds == "") {
        this.msgError("请选择要下载的数据");
        return;
      }
      downLoadZip(
        "/common/batchDownload?bizType=eduHonor&bizIds=" + honorIds,
        "ruoyi"
      );
    },
  },
};
</script>
