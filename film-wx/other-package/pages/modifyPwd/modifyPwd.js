import request from '../../../utils/request'
import { $wuxToptips } from '../../../wux-weapp/index'
Page({

  data: {
    oldPwd:'',//旧密码
    newPwd:'',//新密码
    confirmPwd:'',//确认密码
  },

  
  onLoad: function (options) {

  },
  subimtPwd(e){
    console.log(e);
    this.setData({
      oldPwd:e.detail.value.oldPwd,
      newPwd:e.detail.value.newPwd,
      confirmPwd:e.detail.value.confirmPwd
    })
    if(this.data.oldPwd===''){
      wx.showToast({
        title: '原密码不能为空！',
        icon:'none'
      })
      return;
    }
    if(this.data.newPwd===''){
      wx.showToast({
        title: '新密码不能为空！',
        icon:'none'
      })
      return;
    }
    if(this.data.confirmPwd===''){
      wx.showToast({
        title: '确认密码不能为空！',
        icon:'none'
      })
      return;
    }
    if(this.data.confirmPwd!=this.data.newPwd){
      wx.showToast({
        title: '新密码与确认不一致！',
        icon:'none'
      })
      return;
    }
    request('/system/user/profile/updatePwd','PUT',{
      "Content-Type":"application/x-www-form-urlencoded",
      'Authorization': wx.getStorageSync('token')
    },{
      oldPassword:this.data.oldPwd,
      newPassword:this.data.newPwd
    }).then(res=>{
      console.log(res)
      if(res.code==200){
        $wuxToptips().success({
          hidden: false,
          text: '修改成功',
          duration: 2000,
          success() {},
        })
      }else{
        $wuxToptips().error({
          hidden: false,
          text: res.msg,
          duration: 2000,
          success() {},
        })
      }
    })
  },
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})