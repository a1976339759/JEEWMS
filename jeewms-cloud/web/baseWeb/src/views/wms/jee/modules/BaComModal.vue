<template>
  <j-modal
    :title="title"
    :width="width"
    :visible="visible"
    :confirmLoading="confirmLoading"
    switchFullscreen
    @ok="handleOk"
    @cancel="handleCancel"
    >
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item label="公司代码" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['comCode']" placeholder="请输入公司代码"></a-input>
        </a-form-item>
        <a-form-item label="公司中文简称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['comZhName']" placeholder="请输入公司中文简称"></a-input>
        </a-form-item>
        <a-form-item label="公司中文全称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['comZhAname']" placeholder="请输入公司中文全称"></a-input>
        </a-form-item>
        <a-form-item label="中文地址" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['comZhAddr']" placeholder="请输入中文地址"></a-input>
        </a-form-item>
        <a-form-item label="公司英文简称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['comEnName']" placeholder="请输入公司英文简称"></a-input>
        </a-form-item>
        <a-form-item label="公司英文全称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['comEnAname']" placeholder="请输入公司英文全称"></a-input>
        </a-form-item>

      </a-form>
    </a-spin>
  </j-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'


  export default {
    name: "BaComModal",
    components: { 
    },
    data () {
      return {
        form: this.$form.createForm(this),
        title:"操作",
        width:800,
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
        },
        url: {
          add: "/jeewms/baCom/add",
          edit: "/jeewms/baCom/edit",
        }
      }
    },
    created () {
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'comCode','comZhName','comZhAname','comZhAddr','comEnName','comEnAname'))
        })
      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            console.log("表单提交数据",formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(this.$t('操作成功'));
                that.$emit('ok');
              }else{
                that.$message.warning(this.$t('操作失败'));
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })
          }
         
        })
      },
      handleCancel () {
        this.close()
      },
      popupCallback(row){
        this.form.setFieldsValue(pick(row,'comCode','comZhName','comZhAname','comZhAddr','comEnName','comEnAname'))
      },

      
    }
  }
</script>