const app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    name:'',//用户名
    headerUrl:''//用户头像
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      name: wx.getStorageSync('wxUserInfo').nickName,
      headerUrl:wx.getStorageSync('wxUserInfo').avatarUrl
    })
  
  },
  logout(){
  //  wx.clearStorageSync();
  // wx.removeStorageSync('userInfo');
  wx.removeStorageSync('token');
   app.globalData.flag=false;//设置关闭定时器
   wx.reLaunch({
     url: '/pages/login/login',
   })
  },
  modifyPwd(){
    wx.navigateTo({
      url: '../../other-package/pages/modifyPwd/modifyPwd',
    })
  },
  goOrder(){
    wx.navigateTo({
      url: '../order/order',
    })
  },
  goCollect(){
    wx.navigateTo({
      url: '../collect/collect',
    })
  },
  goHealthCode(){
    wx.navigateTo({
      url: '../healthCode/healthCode',
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