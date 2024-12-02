import { $wuxToptips } from '../../wux-weapp/index'
const app=getApp();
var url=app.globalData.url;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    key:'',
    username:'',
    password:'',
    loading:false,
    power:false,//判断用户是否授权
    powerTotal:0,//只让授权出现一次
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if(wx.getStorageSync('sessionid')){
      wx.reLaunch({
        url: '../index/index',
      })
    }
  },
  getPower(){
    const wxUserInfo=wx.getStorageSync('wxUserInfo');
    // console.log(wxUserInfo);
    if(wxUserInfo!=''){
      this.setData({
        power:true
      })
    }
    if(!this.data.power && this.data.powerTotal===0 && wxUserInfo===''){
      this.setData({
        powerTotal:1
      })
      wx.getUserProfile({
        desc:'用于展示个人信息',
        success:(res)=>{
          console.log(res.userInfo);
          wx.setStorageSync('wxUserInfo', res.userInfo);
          wx.showToast({
            title: '授权成功',
            icon:'none'
          })
          this.setData({
            power:true
          })
        },
        fail:()=>{
          wx.showToast({
            title: '没有授权！授权才可使用',
            icon:'none'
          })
          this.setData({
            powerTotal:0
          })
          return ;
        }
        })
    }
  },
  create_login:function(e){
    if(e.detail.value.username.length==0){
      wx.showToast({
        title: '账号不能为空',
        icon:'error'
      })
      return;
    }
    if(e.detail.value.password.length==0){
      wx.showToast({
        title: '密码不能为空',
        icon:'error'
      })
      return;
    }
    if(!this.data.power)
      return ;
    
     
    this.setData({
      loading:true,
    })
    wx.showLoading({
      title: '登录中',
    })
    console.log(e.detail.value)
    wx.request({

      url: url+'/login',
      method:'POST',
      data:{
        ...e.detail.value
      },
      header: {
        isToken: false
      },
      success:(res)=>{
        console.log(res.data);
        if(res.data.code==200){
          console.log('登陆成功!')
          // wx.setStorageSync('userInfo', res.data.data)//保存用户登录信息
          wx.reLaunch({
            url: '../index/index',
          })
          wx.setStorageSync('token',res.data.token);//保存登陆sessionid
        }else{
          $wuxToptips().error({
            hidden: false,
            text: res.data.msg,
            duration: 2000,
            success() {},
          })
        }
        wx.hideLoading()
      },
      fail:(error) => {
        wx.showToast({
          title: '网络异常！',
          icon: 'error',
          // duration: 1000
        })
      },
    })
    this.setData({
      loading:false
    })
  },
  register(){
    wx.navigateTo({
      url: '../register/register',
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

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})