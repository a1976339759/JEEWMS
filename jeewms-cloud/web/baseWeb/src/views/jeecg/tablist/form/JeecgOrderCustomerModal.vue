<template>
  <j-modal
    :title="title"
    :width="800"
    :visible="visible"
    :confirmLoading="confirmLoading"
    :okButtonProps="{ props: {disabled: disableSubmit} }"
    @ok="handleOk"
    @cancel="handleCancel"
    >

    <!-- 编辑 -->
    <a-spin :spinning="confirmLoading" v-if="editStatus">
      <a-form :form="form">

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="货主姓名"
          hasFeedback>
          <a-input placeholder="请输入货主姓名" v-decorator="['name', {rules: [{ required: true, message: '请输入货主姓名!' }]}]"
                   :readOnly="disableSubmit"/>
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="性别"
          hasFeedback>
          <a-select v-decorator="['sex', {}]" placeholder="请选择性别">
            <a-select-option value="1">男性</a-select-option>
            <a-select-option value="2">女性</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="身份证号码"
          hasFeedback>
          <a-input placeholder="请输入身份证号码" v-decorator="['idcard', validatorRules.idcard]" :readOnly="disableSubmit"/>
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="身份证扫描件"
          hasFeedback>
          <j-image-upload text="上传" v-model="fileList" :isMultiple="true"></j-image-upload>
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="联系方式"
          hasFeedback>
          <a-input v-decorator="[ 'telphone', validatorRules.telphone]" :readOnly="disableSubmit"/>
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="订单号码"
          v-model="this.orderId"
          :hidden="hiding"
          hasFeedback>
          <a-input v-decorator="[ 'orderId', {}]" disabled="disabled"/>
        </a-form-item>
      </a-form>
    </a-spin>
  </j-modal>
</template>

<script>
  import {httpAction} from '@/api/manage'
  import pick from 'lodash.pick'
  import Vue from 'vue'
  import {ACCESS_TOKEN} from "@/store/mutation-types"
  import JImageUpload from '../../../../components/jeecg/JImageUpload'

  export default {
    name: "JeecgOrderCustomerModal",
    components: { JImageUpload },
    data() {
      return {
        title: "操作",
        visible: false,
        model: {},
        labelCol: {
          xs: {span: 24},
          sm: {span: 5},
        },
        wrapperCol: {
          xs: {span: 24},
          sm: {span: 16},
        },
        // 表头
        columns: [
          {
            title: '货主名',
            align: 'left',
            dataIndex: 'name',
          },
          {
            title: '性别',
            align: 'left',
            dataIndex: 'sex',
          },
          {
            title: '身份证号码',
            align: 'left',
            dataIndex: 'idcard',
          },
          {
            title: '身份证扫描件',
            align: 'left',
            dataIndex: 'idcardPic',
          },
          {
            title: '电话',
            dataIndex: 'telphone',
            align: 'left',
          },
          {
            title: '订单号码',
            dataIndex: 'orderId',
            align: 'left',
          },
          {
            title: '创建人',
            dataIndex: 'createBy',
            align: 'left',
          },
          {
            title: '创建时间',
            dataIndex: 'createTime',
            align: 'left',
          },
          {
            title: '更新时间',
            dataIndex: 'updateBy',
            align: 'left',
          },
          {
            title: '更新人',
            dataIndex: 'updateTime',
            align: 'left',
          },
        ],
        fileList: [],
        disableSubmit: false,
        selectedRowKeys: [],
        orderId: "",
        hiding: false,
        headers: {},
        picUrl: "",
        picArray:[],
        previewVisible: false,
        previewImage: '',
        addStatus: false,
        editStatus: false,
        confirmLoading: false,
        form: this.$form.createForm(this),
        url: {
          add: "/test/order/addCustomer",
          edit: "/test/order/editCustomer",
          fileUpload: window._CONFIG['domianURL'] + "/sys/common/upload",
          getOrderCustomerList: "/test/order/listOrderCustomerByMainId",
        },
        validatorRules: {
          telphone: {rules: [{validator: this.validateMobile}]},
          idcard: {rules: [{validator: this.validateIdCard}]}
        },
      }
    },
    computed: {
      uploadAction: function () {
        return this.url.fileUpload;
      }
    },
    created() {
      const token = Vue.ls.get(ACCESS_TOKEN);
      this.headers = {"X-Access-Token": token}
    },
    methods: {
      add(orderId) {
        this.hiding = true;
        if (orderId) {
          this.orderId = orderId;
          this.edit({orderId}, '');
        } else {
          this.$message.warning("请选择一个货主信息");
        }
      },
      detail(record) {
        this.edit(record, 'd');
      },
      edit(record, v) {
        if (v == 'e') {
          this.hiding = false;
          this.disableSubmit = false;
        } else if (v == 'd') {
          this.hiding = false;
          this.disableSubmit = true;
        } else {
          this.hiding = true;
          this.disableSubmit = false;
        }

        this.form.resetFields();
        this.orderId = record.orderId;
        this.model = Object.assign({}, record);
        if (record.id) {
          this.hiding = false;
          this.addStatus = false;
          this.editStatus = true;
          this.$nextTick(() => {
            this.form.setFieldsValue(pick(this.model, 'id', 'name', 'sex', 'idcard','telphone', 'orderId', 'createBy', 'createTime', 'updateBy', 'updateTime'))
          });
          setTimeout(() => {
            this.fileList = record.idcardPic
          }, 5)
        } else {
          this.addStatus = false;
          this.editStatus = true;
        }
        this.visible = true;
      },
      close() {
        this.$emit('close');
        this.visible = false;
        this.picUrl = "";
        this.fileList=[];
      },
      handleOk() {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if (!this.model.id) {
              httpurl += this.url.add;
              method = 'post';
            } else {
              httpurl += this.url.edit;
              method = 'put';
            }
            let formData = Object.assign(this.model, values);
            console.log(formData);
            formData.orderId = this.orderId;
            if(this.fileList != '') {
              formData.idcardPic = this.fileList;
            }else{
              formData.idcardPic = '';
            }
            httpAction(httpurl, formData, method).then((res) => {
              if (res.success) {
                that.$message.success(this.$t('操作成功'));
                that.$emit('ok');
              } else {
                that.$message.warning(this.$t('操作失败'));
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })
          }
        })
      },
      handleCancel() {
        this.close();
      },
      validateMobile(rule, value, callback) {
        if (!value || new RegExp(/^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/).test(value)) {
          callback();
        } else {
          callback("您的手机号码格式不正确!");
        }
      },
      validateIdCard(rule, value, callback) {
        if (!value || new RegExp(/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/).test(value)) {
          callback();
        } else {
          callback("您的身份证号码格式不正确!");
        }
      },
    }
  }
</script>

<style scoped>
  /* tile uploaded pictures */
  .upload-list-inline > > > .ant-upload-list-item {
    float: left;
    width: 200px;
    margin-right: 8px;
  }

  .upload-list-inline > > > .ant-upload-animate-enter {
    animation-name: uploadAnimateInlineIn;
  }

  .upload-list-inline > > > .ant-upload-animate-leave {
    animation-name: uploadAnimateInlineOut;
  }
</style>