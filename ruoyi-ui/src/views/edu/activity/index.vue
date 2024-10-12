<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-row :gutter="20">
        <el-col :span="12">
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
        </el-col>

        <el-col :span="12">
          <el-form-item label="时间" prop="issueTime">
            <el-date-picker clearable size="small"
                            v-model="queryParams.issueTime"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="选择活动时间">
            </el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>

      <el-table-column label="教师" align="center" prop="teacher.teacherName"/>

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
          v-hasPermi="['edu:activity:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['edu:activity:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['edu:activity:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['edu:activity:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="activityList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="社会活动ID" align="center" prop="activityId"/>

      <el-table-column label="教师" align="center" prop="teacher.teacherName"/>
      <el-table-column label="活动详情" align="center" prop="detail"/>
      <el-table-column label="活动时间" align="center" prop="issueTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.issueTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark"/>
      <el-table-column label="链接" align="center" prop="url"/>
      <el-table-column label="附件" align="center" prop="fileUrl"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['edu:activity:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['edu:activity:remove']"
          >删除
          </el-button>
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

    <!-- 添加或修改社会活动对话框 -->
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
        <el-table-column label="教师" align="center" prop="teacher.teacherName"/>
        <el-form-item label="活动详情" prop="detail">
          <el-input v-model="form.detail" placeholder="请输入活动详情"/>
        </el-form-item>
        <el-form-item label="活动时间" prop="issueTime">
          <el-date-picker clearable size="small"
                          v-model="form.issueTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择活动时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="链接" prop="url">
          <el-input v-model="form.url" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="附件" prop="fileUrl">
          <el-input v-model="form.fileUrl" placeholder="请输入附件"/>
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
import {listActivity, getActivity, delActivity, addActivity, updateActivity, exportActivity} from "@/api/edu/activity";
import {listTeacher} from "@/api/edu/teacher";

export default {
  name: "Activity",
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
      // 社会活动表格数据
      activityList: [],
      // 教师选项
      teacherOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        teacherId: null,
        detail: null,
        issueTime: null,
        url: null,
        fileUrl: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    };
  },
  created() {
    this.getList();
    this.loadTeachers();
  },
  methods: {
    loadTeachers() {
      listTeacher().then(response => {
        this.teacherOptions = response.rows;
      });
    },
    /** 查询社会活动列表 */
    getList() {
      this.loading = true;
      listActivity(this.queryParams).then(response => {
        this.activityList = response.rows;
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
        activityId: null,
        teacherId: null,
        detail: null,
        issueTime: null,
        remark: null,
        url: null,
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
      this.ids = selection.map(item => item.activityId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加社会活动";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const activityId = row.activityId || this.ids
      getActivity(activityId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改社会活动";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.activityId != null) {
            updateActivity(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addActivity(this.form).then(response => {
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
      const activityIds = row.activityId || this.ids;
      this.$confirm('是否确认删除社会活动编号为"' + activityIds + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delActivity(activityIds);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 按姓名查询教师 */

    searchTeacher(query) {

      if (query !== '') {

        this.loading = true;

        setTimeout(() => {

          listTeacher({teacherName: query}).then(response => {

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
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有社会活动数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.exportLoading = true;
        return exportActivity(queryParams);
      }).then(response => {
        this.download(response.msg);
        this.exportLoading = false;
      }).catch(() => {
      });
    }
  }
};
</script>
