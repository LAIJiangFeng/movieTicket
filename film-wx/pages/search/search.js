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
        query:{
          reasonable:false,//分页插件解决当请求的页码数小于0时，返回第一页数据，页码数大于总页数时，会返回最后一页的数据的问题。
          pageNum:1,
          pageSize:7,
          name:null,
        },
        loadAll:false,
        isSetTimeout:true,
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {

    },
    getList(type){//获取设备数据
        if(type!='scroll'){
          this.setData({
            'query.pageNum':1
          })
        }
        request('/film/film/list?'+tansParams(this.data.query),'GET',{
          'content-type' : "application/json;charset=utf-8",
          'Authorization': wx.getStorageSync('token')
        },{}).then(res=>{
            console.log(res)
            let rows=[]
            if(type=='scroll'){
              rows=[...this.data.film,...res.rows]
            }else{
              rows=res.rows
            }
            console.log(rows)
            //加载完了
            if(res.rows.length==0 ){
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
    cancel(e){
        wx.switchTab({
            url: '../film/film',
        })
    },
    change(e){
        this.setData({
            'query.name':e.detail.value
        })
        if(!this.data.query.name){
            this.clear()
            return;
        }
        if(this.data.isSetTimeout){
            this.setData({
                isSetTimeout:false
            })
            setTimeout(() => {
                this.getList()
                this.setData({
                    isSetTimeout:true
                })
            }, 1000);
        }
    },
    clear(){
        this.setData({
            film:[],
            loadAll:false,
        })
    },
    goDetails(e){
        wx.navigateTo({
            url: '../details/details?id='+e.currentTarget.id,
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
        this.getList('scroll')
    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    }
})