import request from '../../utils/request'
const app=getApp();
var url=app.globalData.url;
Page({
 
    /**
     * 页面的初始数据
     */
    data: {
      url:url,//路径
      src:"../../static/images/bg-upload.png",
      photonew:"",
      phone:'',
      temperatureStatus:'',
      healthCodeColor:'',
      bodyStatus:'',
      inoculationStatus:'',
      upload:false,//是否上报健康码
      disabled:false,//是否可以修改
    },
   
  selectUploadFHealthy(){
    request('/film/healthy/selectUploadFHealthy','GET',{
      'content-type' : "application/json;charset=utf-8",
      'Authorization': wx.getStorageSync('token')
    },{}).then(res=>{
      console.log(res)
      if(res){
        this.setData({
          src:this.data.url+res.img,
          photonew:res.img,
          phone:res.phone,
          upload:true,
          disabled:true,
          temperatureStatus:res.temperatureStatus,
          bodyStatus:res.bodyStatus,
          healthCodeColor:res.healthCodeColor,
          inoculationStatus:res.inoculationStatus,
        })
      }
    })
  },
  //选择图片
  chooseImage :function(e){
    //已经上传完成开启预览
    if(this.data.upload){
      wx.previewImage({
        // 当前显示图片的http链接
        urls: [this.data.src] // 需要预览的图片http链接列表
      });
      return;
    }
    var that = this;
    wx.chooseMedia({
      count:1,
      mediaType:['image'],
      success(res){
       that.setData({
        src:res.tempFiles[0].tempFilePath
       })
       that.uploadImage()
      }
    })
  },
  uploadImage :function(e){
    var that =this;
    wx.uploadFile({
      filePath: that.data.src,//文件地址
      // 参数名和接口一致
      name: 'file',
      url: this.data.url+'/common/upload',
      header: { 
          "Content-Type": "multipart/form-data" ,
          'Authorization': wx.getStorageSync('token')
      },
      success(res){
       console.log(JSON.parse(res.data))
       that.setData({
            photonew:JSON.parse(res.data).fileName
       })
        wx.showToast({
          title: '图片上传成功',
          icon: 'success',
          duration: 2000
        })
      },fail(res){
        console.log(res)
        wx.showToast({
          title: '图片上传失败',
          icon: 'error',
          duration: 2000
        })
      }
    })
  },
  changeTemperature(e){
    this.setData({
      temperatureStatus:e.detail.value
    })
  },
  changeBody(e){
    this.setData({
      bodyStatus:e.detail.value
    })
  },
  changeHealthCode(e){
    this.setData({
      healthCodeColor:e.detail.value
    })
  },
  changeInoculation(e){
    this.setData({
      inoculationStatus:e.detail.value
    })
  },
  subimt(e){
    this.setData({
      phone:e.detail.value.phone,
    })
    if(this.data.phone===''){
      wx.showToast({
        title: '手机不能为空',
        icon:'none'
      })
      return;
    }
    if(this.data.temperatureStatus===''){
      wx.showToast({
        title: '请选择目前体温',
        icon:'none'
      })
      return;
    }
    if(this.data.bodyStatus===''){
      wx.showToast({
        title: '请选择身体状况',
        icon:'none'
      })
      return;
    }
    if(this.data.healthCodeColor===''){
      wx.showToast({
        title: '请选择健康码颜色',
        icon:'none'
      })
      return;
    }
    if(this.data.inoculationStatus===''){
      wx.showToast({
        title: '请选择疫苗接种情况',
        icon:'none'
      })
      return;
    }
    // if(this.data.src==='' || this.data.src==='../../static/images/bg-upload.png'){
    //   wx.showToast({
    //     title: '请上传健康码',
    //     icon:'none'
    //   })
    //   return;
    // }
    request('/film/healthy','POST',{
      'content-type' : "application/json;charset=utf-8",
      'Authorization': wx.getStorageSync('token')
    },{
      phone:this.data.phone,
      temperatureStatus:this.data.temperatureStatus,
      bodyStatus:this.data.bodyStatus,
      healthCodeColor:this.data.healthCodeColor,
      inoculationStatus:this.data.inoculationStatus,
      // img:this.data.photonew
    }).then(res=>{
      console.log(res)
      if(res.code==200){
        wx.showToast({
          title: '上报成功',
        })
        this.setData({
          upload:true
        })
      }else{
        wx.showToast({
          title: '上报失败',
          icon:'error'
        })
      }
    })
  },
   
   
   
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
      this.selectUploadFHealthy()
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