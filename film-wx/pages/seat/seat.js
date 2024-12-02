import request from '../../utils/request'
Page({

    /**
     * 页面的初始数据
     */
    data: {
        film:{
            id:'1',
            name:'红发歌姬',
            price:39.9
        },//电影信息
        seatList:[],//座位
        selectList:[],//选择的座位
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        console.log(JSON.parse(options.film))
        if(options.film){
            this.setData({
                film:JSON.parse(options.film)
            })
        }
        this.getList()
    },
    getList(){
        request('/film/order/getSeat/'+this.data.film.id,'GET',{
          'content-type' : "application/json;charset=utf-8",
          'Authorization': wx.getStorageSync('token')
        },{}).then(res=>{
            console.log(res)
            this.setData({
                seatList:res
            })
        }).catch(e=>{
        })
    },
    selectSeat(e){
        console.log(e)
        if(this.data.selectList.length<5){
            if(!e.currentTarget.dataset.item.disabled){
                //取反原来的选择
                this.data.seatList[e.currentTarget.dataset.item.number-1].select=!this.data.seatList[e.currentTarget.dataset.item.number-1].select
                if(e.currentTarget.dataset.item.select){
                    //找到选择的数据项并删除
                    for(let i=0;i<this.data.selectList.length;i++){
                        if(this.data.selectList[i].number==e.currentTarget.dataset.item.number)
                            this.data.selectList.splice(i, 1)
                    }
                    this.setData({
                        seatList:this.data.seatList,
                        selectList:this.data.selectList
                    })
                }else{
                    this.data.selectList.push({number:e.currentTarget.dataset.item.number,price:this.data.film.price})
                    this.setData({
                        seatList:this.data.seatList,
                        selectList:this.data.selectList
                    })
                }
            }else{
                wx.showToast({
                    title: '此座已有人购买',
                    icon:'none'
                })
            }
        }else{
            wx.showToast({
              title: '一次最多购买5张票',
              icon:'none'
            })
        }
    },
    buy(){
        var that=this
        if(this.data.selectList.length==0){
            wx.showToast({
                icon:'none',
                title: '请选择座位',
            })
            return;
        }
        wx.showModal({
          title:'微信支付',
          confirmColor:'#42742f',
          confirmText: '确认支付',
          content:'￥' +(that.data.film.price *that.data.selectList.length).toFixed(1) +'元,确认购票 ?',
          success (res) {
              if (res.confirm) {
                  let seat='';
                  for(let i=0;i<that.data.selectList.length;i++){
                        seat+=that.data.selectList[i].number+','
                  }
                  request('/film/order','POST',{
                      'content-type' : "application/json;charset=utf-8",
                      'Authorization': wx.getStorageSync('token')
                      },{
                        filmId:that.data.film.id,
                        count:that.data.selectList.length,
                        totalPrice:(that.data.film.price *that.data.selectList.length).toFixed(1),
                        seat:seat.length>0?seat.substring(0,seat.length-1):seat,
                      }).then(res=>{
                          console.log(res)
                          if(res.code=200){
                              wx.showToast({
                                  title: '购买成功',
                              })
                          }
                      }).catch(e=>{
                      })
              } else if (res.cancel) {
                wx.showToast({
                  icon:'none',
                  title: '取消支付',
                })
              }
          }
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