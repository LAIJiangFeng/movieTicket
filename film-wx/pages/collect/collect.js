import request from '../../utils/request'
import {tansParams} from '../../utils/util'
const app=getApp();
var url=app.globalData.url;
Page({

    /**
     * 页面的初始数据
     */
    data: {
        film:[],//电影
        url:url,//图片路径头
        loadAll:false, //是否加载完毕
        query:{
            reasonable:false,//分页插件解决当请求的页码数小于0时，返回第一页数据，页码数大于总页数时，会返回最后一页的数据的问题。
            pageNum:1,
            pageSize:7,
        },
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        this.getList();
    },
    getList(){//获取设备数据
        request('/film/collection/list?'+tansParams(this.data.query),'GET',{
          'content-type' : "application/json;charset=utf-8",
          'Authorization': wx.getStorageSync('token')
        },{}).then(res=>{
            console.log(res)
            let rows=[...this.data.film,...res.rows]
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
            this.setData({
              film:rows
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
    collect(e){
        var that=this
        console.log(e)
        wx.showModal({
            title:'提示',
            content:'确认取消收藏'+this.data.film[e.currentTarget.dataset.index].name+'?',
            success (res) {
                if (res.confirm) {
                    request('/film/collection/removeFCollectionByFilmIdAndUserName','DELETE',{
                        'content-type' : "application/json;charset=utf-8",
                        'Authorization': wx.getStorageSync('token')
                        },{filmId:e.currentTarget.dataset.id}).then(res=>{
                            console.log(res)
                            that.data.film.splice(e.currentTarget.dataset.index,1)
                            that.setData({
                                film:that.data.film
                            })
                            if(res.code=200){
                                wx.showToast({
                                    title: '已取消收藏',
                                })
                            }
                        }).catch(e=>{
                        })
                } else if (res.cancel) {
                    console.log('用户点击取消')
                }
            }
        })
    
    },
    buy(e){
        request('/film/film/'+e.currentTarget.id,'GET',{
            'content-type' : "application/json;charset=utf-8",
            'Authorization': wx.getStorageSync('token')
          },{}).then(res=>{
              console.log(res)
              wx.navigateTo({
                url: '../seat/seat?film='+JSON.stringify(res.data),
            })
          }).catch(e=>{
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
        this.setData({
            'query.pageNum':this.data.query.pageNum+1
          })
        this.getList()
    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    }
})