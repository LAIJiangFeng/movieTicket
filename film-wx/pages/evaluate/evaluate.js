import request from '../../utils/request'
Page({

    /**
     * 页面的初始数据
     */
    data: {
        form:{
            filmId:null,
            score:10,
            content:'',
        },
        name:'',//电影名称
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        if(options.id){//传过来的id
            this.setData({
              'form.filmId':options.id,
            })
        }
        if(options.name){//传过来的name
            this.setData({
              'name':options.name,
            })
        }
    },
    onChange(e){
        this.setData({
            'form.score':e.detail.value*2
        })
    },
    submit(e){
        console.log(e)
        if(e.detail.value.content===''){
            wx.showToast({
                title: '内容不能为空',
                icon:'none'
            })
            return;
        }
        this.setData({
            'form.content':e.detail.value.content
        })
        request('/film/comment','POST',{
            'content-type' : "application/json;charset=utf-8",
            'Authorization': wx.getStorageSync('token')
        },this.data.form).then(res=>{
            console.log(res)
            if(res.code=200){
                wx.showToast({
                    title: '发布成功',
                })
                setTimeout(_=>{
                    wx.navigateBack()
                },1500)
            }
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

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    }
})