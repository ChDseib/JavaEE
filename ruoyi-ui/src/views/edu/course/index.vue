<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="负责人" prop="teacherId">
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
      <el-form-item label="课程名称" prop="courseName">
        <el-input
          v-model="queryParams.courseName"
          placeholder="请输入课程名称"
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
          v-hasPermi="['edu:course:add']"
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
          v-hasPermi="['edu:course:edit']"
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
          v-hasPermi="['edu:course:remove']"
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
          v-hasPermi="['edu:course:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="courseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" align="center">
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="课程名称" align="center" prop="courseName" width="250"/>
      <el-table-column label="负责人" align="center" prop="teacher.teacherName" />
      <el-table-column label="级别" align="center" prop="level" >
        <template slot-scope="scope">
          <dict-tag :options="levelOptions" :value="scope.row.level"/>
        </template>
      </el-table-column>
      <el-table-column label="类别" align="center" prop="courseType" >
        <template slot-scope="scope">
          <dict-tag :options="courseOptions" :value="scope.row.courseType"/>
        </template>
      </el-table-column>

      <el-table-column label="认定时间" align="center" prop="issueTime" width="180">
      </el-table-column>
      <el-table-column label="上级拨款" align="center" prop="externalAmount" width="100"/>
      <el-table-column label="校内配套" align="center" prop="internalAmount" width="100"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['edu:course:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['edu:course:remove']"
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

    <!-- 添加或修改课程对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="负责人" prop="teacherId" :rules="[{required:true,message:'请输入负责人',trigger:'blur'}]">
          <el-select v-model="form.teacherId" placeholder="请输入负责人" clearable size="small"
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
        <el-form-item label="课程名称" prop="courseName" :rules="[{required:true,message:'请输入课程名称',trigger:'blur'}]">
          <el-input v-model="form.courseName" placeholder="请输入课程名称" />
        </el-form-item>
        <el-form-item label="级别" prop="level">
          <el-select v-model="form.level" placeholder="请选择级别" clearable size="small">
            <el-option
              v-for="dict in levelOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="类别" prop="courseType">
          <el-select v-model="form.courseType" placeholder="请选择类别">
            <el-option
              v-for="dict in courseOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="认定时间" prop="issueTime">
          <el-date-picker clearable size="small"
            v-model="form.issueTime"
            type="year"
            value-format="yyyy"
            placeholder="选择认定时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="上级拨款" prop="externalAmount" >
          <el-input v-model="form.externalAmount" placeholder="请输入上级拨款" :value="0"/>
        </el-form-item>
        <el-form-item label="校内配套" prop="internalAmount">
          <el-input v-model="form.internalAmount" placeholder="请输入校内配套" :value="0" />
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
import { listCourse, getCourse, delCourse, addCourse, updateCourse, exportCourse } from "@/api/edu/course";
import {listTeacher} from "@/api/edu/teacher";

export default {
  name: "Course",
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
      // 课程表格数据
      courseList: [],
      // 级别字典
      levelOptions: [],
      // 课程类别字典
      courseOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      teacherOptions:[],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        teacherId: null,
        courseName: null,
        level: null,
        courseType: null,
        issueTime: null,
        externalAmount: null,
        internalAmount: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("edu_contest_level").then(response => {
      this.levelOptions = response.data;
    });
    this.getDicts("edu_course_type").then(response => {
      this.courseOptions = response.data;
    });
  },
  methods: {
    /** 按姓名查询教师（负责人） */
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
    /** 查询课程列表 */
    getList() {
      this.loading = true;
      listCourse(this.queryParams).then(response => {
        this.courseList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },

    changeValue(value) {
      this.form.internalAmount = /^[0-9]*$/.test(parseInt(value)) ? String(parseInt(value)).replace(".", "") : ""
    },


    // 表单重置
    reset() {
      this.form = {
        courseId: null,
        teacherId: null,
        courseName: null,
        level: null,
        courseType: null,
        issueTime: null,
        externalAmount: null,
        internalAmount: null
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
      console.log(selection)
      this.ids = selection.map(item => item.courseId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加课程";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const courseId = row.courseId || this.ids
      getCourse(courseId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改课程";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.courseId != null) {
            updateCourse(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCourse(this.form).then(response => {
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
      console.log(this.index)
      const courseIds = row.courseId || this.ids;
      this.$confirm('是否确认删除选中的课程数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delCourse(courseIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有课程数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportCourse(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
