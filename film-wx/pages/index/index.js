import request from '../../utils/request'
const app=getApp();
var url=app.globalData.url;
var chart=null;//pie图的chart
Page({
  data: {
    swiperImgList:[],//公共轮播图
    url:url,//路径
    shownList:[], //上映电影
    notShownList:[],//未上映电影
    likeList:[],//猜你喜欢电影
  },

  onLoad: async function (options) {
   
  },
  getList(){
    request('/film/film/selectFilmIndex','GET',{
      'content-type' : "application/json;charset=utf-8",
      'Authorization': wx.getStorageSync('token')
    },{}).then(res=>{
        console.log(res)
        this.setData({
          swiperImgList:res.swiperImgList,
          shownList:res.shownList,
          notShownList:res.notShownList,
          likeList:res.likeList,
        })
    }).catch(e=>{
    })
  },
  goDetails(e){
    console.log(e)
    wx.navigateTo({
      url: '../details/details?id='+e.currentTarget.id,
    })
  },
  goFilm(e){
    console.log(e)
    wx.reLaunch({
      url: '../film/film?released='+e.currentTarget.dataset.released,
    })
  },
  collect(e){
    console.log(e)
    if(e.currentTarget.dataset.collect){
      request('/film/collection/removeFCollectionByFilmIdAndUserName','DELETE',{
        'content-type' : "application/json;charset=utf-8",
        'Authorization': wx.getStorageSync('token')
      },{filmId:e.currentTarget.dataset.id}).then(res=>{
          console.log(res)
          this.data.notShownList[e.currentTarget.dataset.index].collect=!this.data.notShownList[e.currentTarget.dataset.index].collect
          this.setData({
            notShownList:this.data.notShownList
          })
          if(res.code=200){
            wx.showToast({
              title: '已取消收藏',
            })
          }
      }).catch(e=>{
      })
    }else{
      request('/film/collection','POST',{
        'content-type' : "application/json;charset=utf-8",
        'Authorization': wx.getStorageSync('token')
      },{filmId:e.currentTarget.dataset.id}).then(res=>{
          console.log(res)
          this.data.notShownList[e.currentTarget.dataset.index].collect=!this.data.notShownList[e.currentTarget.dataset.index].collect
          this.setData({
            notShownList:this.data.notShownList
          })
          if(res.code=200){
            wx.showToast({
              title: '已收藏',
            })
          }
      }).catch(e=>{
      })
    }
  },
  buy(e){
    wx.navigateTo({
      url: '../seat/seat?film='+JSON.stringify(e.currentTarget.dataset.film),
    })
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
    this.getList();
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
