<template>
  <a-drawer
    :title="title"
    :maskClosable="true"
    width=650
    placement="right"
    :closable="true"
    @close="close"
    :visible="visible"
    style="overflow: auto;padding-bottom: 53px;">

    <a-form>
      <a-form-item :label="$t('所拥有的权限')">
        <a-tree
          checkable
          @check="onCheck"
          :checkedKeys="checkedKeys"
          :treeData="treeData"
          @expand="onExpand"
          @select="onTreeNodeSelect"
          :selectedKeys="selectedKeys"
          :expandedKeys="expandedKeysss"
          :checkStrictly="checkStrictly">
          <span slot="hasDatarule" slot-scope="{slotTitle,ruleFlag}">
            {{ $t(slotTitle)  }}<a-icon v-if="ruleFlag" type="align-left" style="margin-left:5px;color: red;"></a-icon>
          </span>
        </a-tree>
      </a-form-item>
    </a-form>

    <div class="drawer-bootom-button">
      <a-dropdown style="float: left" :trigger="['click']" placement="topCenter">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="switchCheckStrictly(1)">{{$t('父子关联')}}</a-menu-item>
          <a-menu-item key="2" @click="switchCheckStrictly(2)">{{$t('取消关联')}}</a-menu-item>
          <a-menu-item key="3" @click="checkALL">{{$t('全部勾选')}}</a-menu-item>
          <a-menu-item key="4" @click="cancelCheckALL">{{$t('取消全选')}}</a-menu-item>
          <a-menu-item key="5" @click="expandAll">{{$t('展开所有')}}</a-menu-item>
          <a-menu-item key="6" @click="closeAll">{{$t('合并所有')}}</a-menu-item>
        </a-menu>
        <a-button>
          {{$t('树操作')}}<a-icon type="up" />
        </a-button>
      </a-dropdown>
      <a-popconfirm :title="$t('确定放弃编辑?')" @confirm="close" :okText="$t('确定')" :cancelText="$t('取消')">
        <a-button style="margin-right: .8rem">{{$t('取消')}}</a-button>
      </a-popconfirm>
      <a-button @click="handleSubmit(false)" type="primary" :loading="loading" ghost style="margin-right: 0.8rem">{{$t('仅保存')}}</a-button>
      <a-button @click="handleSubmit(true)" type="primary" :loading="loading">{{$t('保存并关闭')}}</a-button>
    </div>

    <role-datarule-modal ref="datarule"></role-datarule-modal>

  </a-drawer>

</template>
<script>
  import {queryTreeListForRole,queryRolePermission,saveRolePermission} from '@/api/api'
  import RoleDataruleModal from './RoleDataruleModal.vue'

  export default {
    name: "RoleModal",
    components:{
      RoleDataruleModal
    },
    data(){
      return {
        roleId:"",
        treeData: [],
        defaultCheckedKeys:[],
        checkedKeys:[],
        expandedKeysss:[],
        allTreeKeys:[],
        autoExpandParent: true,
        checkStrictly: true,
        title: this.$t('角色权限配置'),
        visible: false,
        loading: false,
        selectedKeys:[]
      }
    },
    methods: {
      onTreeNodeSelect(id){
        if(id && id.length>0){
          this.selectedKeys = id
        }
        this.$refs.datarule.show(this.selectedKeys[0],this.roleId)
      },
      onCheck (o) {
        if(this.checkStrictly){
          this.checkedKeys = o.checked;
        }else{
          this.checkedKeys = o
        }
      },
      show(roleId){
        this.roleId=roleId
        this.visible = true;
      },
      close () {
        this.reset()
        this.$emit('close');
        this.visible = false;
      },
      onExpand(expandedKeys){
        this.expandedKeysss = expandedKeys;
        this.autoExpandParent = false
      },
      reset () {
        this.expandedKeysss = []
        this.checkedKeys = []
        this.defaultCheckedKeys = []
        this.loading = false
      },
      expandAll () {
        this.expandedKeysss = this.allTreeKeys
      },
      closeAll () {
        this.expandedKeysss = []
      },
      checkALL () {
        this.checkedKeys = this.allTreeKeys
      },
      cancelCheckALL () {
        //this.checkedKeys = this.defaultCheckedKeys
        this.checkedKeys = []
      },
      switchCheckStrictly (v) {
        if(v==1){
          this.checkStrictly = false
        }else if(v==2){
          this.checkStrictly = true
        }
      },
      handleCancel () {
        this.close()
      },
      handleSubmit(exit) {
        let that = this;
        let params =  {
          roleId:that.roleId,
          permissionIds:that.checkedKeys.join(","),
          lastpermissionIds:that.defaultCheckedKeys.join(","),
        };
        that.loading = true;
        console.log("请求参数：",params);
        saveRolePermission(params).then((res)=>{
          if(res.success){
            that.$message.success(this.$t('操作成功'));
            that.loading = false;
            if (exit) {
              that.close()
            }
          }else {
            that.$message.error(this.$t('操作失败'));
            that.loading = false;
            if (exit) {
              that.close()
            }
          }
          this.loadData();
        })
      },
      loadData(){
        queryTreeListForRole().then((res) => {
          this.treeData = res.result.treeList
          this.allTreeKeys = res.result.ids
          queryRolePermission({roleId:this.roleId}).then((res)=>{
              this.checkedKeys = [...res.result];
              this.defaultCheckedKeys = [...res.result];
              this.expandedKeysss = this.allTreeKeys;
              console.log(this.defaultCheckedKeys)
          })
        })
      }
    },
  watch: {
    visible () {
      if (this.visible) {
        this.loadData();
      }
    }
  }
  }

</script>
<style lang="less" scoped>
  .drawer-bootom-button {
    position: absolute;
    bottom: 0;
    width: 100%;
    border-top: 1px solid #e8e8e8;
    padding: 10px 16px;
    text-align: right;
    left: 0;
    background: #fff;
    border-radius: 0 0 2px 2px;
  }

</style>