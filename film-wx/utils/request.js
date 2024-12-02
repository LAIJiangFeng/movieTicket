var app=getApp();
var BaseURL=app.globalData.url;
export default(url,method='GET',header={},data={})=>{
  return new Promise((resolve,reject)=>{
    wx.request({
      url: BaseURL+url,
      method:method,
      header:header,
      data:data,
      success:(res)=>{
        // console.log(res)
        // console.log( res.data.errorMsg.indexOf('未登录')!=-1)
        if(res.data.errorCode && res.data.errorCode==404 || (res.data.errorCode===-1&& res.data.errorMsg.indexOf('未登录')!=-1)){
          wx.removeStorageSync('userInfo');
          wx.removeStorageSync('sessionid');
          app.globalData.flag=false;//设置关闭定时器
          wx.reLaunch({
            url: '/pages/login/login',
          })
        }
        resolve(res.data);
        // console.log(res);
      },
      fail:(res)=>{
        wx.showToast({
          title: '网络错误！',
          icon:'error',
          duration: 1000
        })
      }
    })
  })
}