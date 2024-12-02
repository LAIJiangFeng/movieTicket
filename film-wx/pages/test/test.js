// pages/test/test.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    shows: false, //控制下拉列表的显示隐藏，false隐藏、true显示
    selectDatas: ['消费账户', '平台返利账户', '微信钱包'], //下拉列表的数据
    indexs: 0, //选择的下拉列 表下标,
    url:'',
    open: false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },
  onChange(e) {
    var that = this;
    that.setData({
      open: !that.data.open
    })
  },
// 点击下拉显示框
selectTaps() {
  this.setData({
    shows: !this.data.shows,
  });
},
// 点击下拉列表
optionTaps(e) {
  let Indexs = e.currentTarget.dataset.index; //获取点击的下拉列表的下标
  console.log(Indexs)
  this.setData({
    indexs: Indexs,
    shows: !this.data.shows
  });

},
goUserInfo(){
  // wx.getUserInfo({
  //   lang: "en",
  //   success:(res)=>{
  //     var userInfo=res.userInfo;
  //     this.setData({
  //       url:userInfo.avatarUrl
  //     })
  //     console.log(userInfo);
  //     console.log(res.rawData);
  //     console.log(res.encryptedData)
  //     console.log(userInfo.nickName);
  //     console.log(userInfo.avatarUrl);
  //     console.log(userInfo.country);
  //   }
  // })
//   wx.getUserProfile({
//     desc: '获取您的昵称及头像',
//     success:(res)=>{
//       console.log(res.userInfo);
//       this.setData({
//         url:res.userInfo.avatarUrl
//       })
//     }
//   })
},
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
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