import request from '../../utils/request'
import {tansParams} from '../../utils/util'
const app=getApp();
var url=app.globalData.url;
Page({
  data: {
    dataTree: [],
    selectKey: '', //选中的节点id
    film:[],//电影
    url:url,//图片路径头
    isHidden:true,//是否隐藏菜单
    query:{
      reasonable:false,//分页插件解决当请求的页码数小于0时，返回第一页数据，页码数大于总页数时，会返回最后一页的数据的问题。
      released:1,
      pageNum:1,
      pageSize:7,
      type:null,
    },
    loadAll:false
  },
  onLoad: function (options) {
    if(options.released){//传过来的id
      this.setData({
        'query.released':options.released,
      })
    }
   this.getList();
   this.getMenu();
  },
  handleSelect(e) {
    console.log(e)
    this.setData({
      selectKey: e.detail.item.dictCode==this.data.selectKey?'': e.detail.item.dictCode,
      'query.type':e.detail.item.dictValue==this.data.query.type?null:e.detail.item.dictValue,
    })
    this.getList();
},
  isHiddenMenu(){//是否隐藏菜单
    this.setData({
      isHidden:!this.data.isHidden
    })
  },
  selectType(e){
    this.setData({
      'query.released':e.target.dataset.index
    })
    this.getList()
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
        if(res.rows.length==0 && this.data.film.length>0){
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
        wx.hideLoading()
    }).catch(e=>{
    })
  },
  getMenu(){//获取菜单分类
    request('/system/dict/data/getFilmType','GET',{
      'content-type' : "application/json;charset=utf-8",
      'Authorization': wx.getStorageSync('token')
    },{}).then(res=>{
        console.log(res)
        this.setData({
          dataTree:res
        })
    }).catch(e=>{
    })
  },
  filmDetails(e){//设备详情
    wx.navigateTo({
      url: '../details/details?id='+e.currentTarget.id,
    })
  },
  goDetails(e){
    console.log(e)
    wx.navigateTo({
      url: '../details/details?id='+e.currentTarget.id,
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
          this.data.film[e.currentTarget.dataset.index].collect=!this.data.film[e.currentTarget.dataset.index].collect
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
          this.data.film[e.currentTarget.dataset.index].collect=!this.data.film[e.currentTarget.dataset.index].collect
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
  search(){
    wx.navigateTo({
      url: '../search/search',
    })
  },
  buy(e){
    wx.navigateTo({
      url: '../seat/seat?film='+JSON.stringify(e.currentTarget.dataset.film),
    })
  },
  onHide: function () {
  },
  //监听下拉触底
  onReachBottom(){
    this.setData({
      'query.pageNum':this.data.query.pageNum+1
    })
    this.getList('scroll')
  }
})
