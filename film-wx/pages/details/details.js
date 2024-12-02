import request from '../../utils/request'
import {tansParams} from '../../utils/util'
const app=getApp();
var url=app.globalData.url;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    url:url,
    id:0,//选中id
    film:{},//电影
    content:[],//评论
    loadAll:false,//是否加载完
    query:{
      filmId:null,
      reasonable:false,//分页插件解决当请求的页码数小于0时，返回第一页数据，页码数大于总页数时，会返回最后一页的数据的问题。
      pageNum:1,
      pageSize:5,
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: async function (options) {
    if(options.id){//传过来的id
      this.setData({
        id:options.id,
        'query.filmId':options.id,
      })
    }
    this.getInfo();
    this.getContent();
  },
  getInfo(){
    request('/film/film/'+this.data.id,'GET',{
      'content-type' : "application/json;charset=utf-8",
      'Authorization': wx.getStorageSync('token')
    },{}).then(res=>{
        console.log(res)
        this.setData({
          film:res.data
        })
    }).catch(e=>{
    })
  },
  getContent(){
    request('/film/comment/list?'+tansParams(this.data.query),'GET',{
      'content-type' : "application/json;charset=utf-8",
      'Authorization': wx.getStorageSync('token')
    },{}).then(res=>{
      let rows=[...this.data.content,...res.rows]
      this.setData({
        content:rows
      })
      //加载完了
      if(res.rows.length==0){
        this.setData({
          loadAll:true
        })
      }else{
        this.setData({
          loadAll:false
        })
      }
    }).catch(e=>{
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
          this.data.film.collect=!this.data.film.collect
          this.setData({
            film:this.data.film
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
          this.data.film.collect=!this.data.film.collect
          this.setData({
            film:this.data.film
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
    console.log(e)
    wx.navigateTo({
      url: '../seat/seat?film='+JSON.stringify(this.data.film),
    })
    // var that=this
    // wx.showModal({
    //   title:'微信支付',
    //   confirmColor:'#42742f',
    //   confirmText: '确认支付',
    //   content:'￥' +this.data.film.price+'元（张）确认购票 ?',
    //   success (res) {
    //       if (res.confirm) {
    //           request('/film/order','POST',{
    //               'content-type' : "application/json;charset=utf-8",
    //               'Authorization': wx.getStorageSync('token')
    //               },{filmId:e.currentTarget.id}).then(res=>{
    //                   console.log(res)
    //                   if(res.code=200){
    //                       wx.showToast({
    //                           title: '购买成功',
    //                       })
    //                   }
    //               }).catch(e=>{
    //               })
    //       } else if (res.cancel) {
    //         wx.showToast({
    //           icon:'none',
    //           title: '取消支付',
    //         })
    //       }
    //   }
    // })
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

  //监听下拉触底
  onReachBottom(){
    this.setData({
      'query.pageNum':this.data.query.pageNum+1
    })
    this.getContent()
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})