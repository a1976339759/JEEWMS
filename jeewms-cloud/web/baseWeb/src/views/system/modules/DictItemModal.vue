<template>
  <j-modal
    :title="title"
    :width="800"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    
  >
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          :label="$t('名称')">
          <a-input :placeholder="$t('请输入')" v-decorator.trim="['itemText', validatorRules.itemText]"/>
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          :label="$t('数据值')">
          <a-input :placeholder="$t('请输入')" v-decorator.trim="['itemValue', validatorRules.itemValue]"/>
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          :label="$t('描述')">
          <a-input v-decorator="['description']"/>
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          :label="$t('排序值')">
          <a-input-number :min="1" v-decorator="['sortOrder',{'initialValue':1}]"/>
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          :label="$t('是否启用')"
          hasFeedback>
          <a-switch :checkedChildren="$t('启用')" :unCheckedChildren="$t('禁用')" @change="onChose" v-model="visibleCheck"/>
        </a-form-item>

      </a-form>
    </a-spin>
  </j-modal>
</template>

<script>
  import pick from 'lodash.pick'
  import {addDictItem, editDictItem} from '@/api/api'

  export default {
    name: "DictItemModal",
    data() {
      return {
        title: "操作",
        visible: false,
        visibleCheck: true,
        model: {},
        dictId: "",
        status: 1,
        labelCol: {
          xs: {span: 24},
          sm: {span: 5},
        },
        wrapperCol: {
          xs: {span: 24},
          sm: {span: 16},
        },
        confirmLoading: false,
        form: this.$form.createForm(this),
        validatorRules: {
          itemText: {rules: [{required: true, message: this.$t('请输入')}]},
          itemValue: {rules: [{required: true, message: this.$t('请输入')},{validator: this.validateItemValue}]},
        },
      }
    },
    created() {
    },
    methods: {
      add(dictId) {
        this.dictId = dictId;
        this.edit({});
      },
      edit(record) {
        if (record.id) {
          this.dictId = record.dictId;
          this.visibleCheck = (record.status == 1) ? true : false;
        }
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.model.dictId = this.dictId;
        this.model.status = this.status;
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model, 'itemText', 'itemValue', 'description', 'sortOrder'))
        });
      },
      onChose(checked) {
        if (checked) {
          this.status = 1;
          this.visibleCheck = true;
        } else {
          this.status = 0;
          this.visibleCheck = false;
        }
      },
      // 确定
      handleOk() {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            values.itemText = (values.itemText || '').trim()
            values.itemValue = (values.itemValue || '').trim()
            values.description = (values.description || '').trim()
            let formData = Object.assign(this.model, values);
            formData.status = this.status;
            let obj;
            if (!this.model.id) {
              obj = addDictItem(formData);
            } else {
              obj = editDictItem(formData);
            }
            obj.then((res) => {
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
      // 关闭
      handleCancel() {
        this.close();
      },
      close() {
        this.$emit('close');
        this.visible = false;
      },
      validateItemValue(rule, value, callback){
        if(value){
        //   let reg=new RegExp("[`_~!@#$^&*()=|{}'.<>《》/?！￥（）—【】‘；：”“。，、？]")
        //   if(reg.test(value)){
        //     callback("数据值不能包含特殊字符！")
        //   }else{
        //     callback()
        //   }
        // }else{
          callback()
        }
      }
    }
  }
</script>
