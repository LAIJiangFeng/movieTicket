import request from '../../utils/request'
Page({

    /**
     * 页面的初始数据
     */
    data: {
        nickName:null,
        userName:null,
        phonenumber:null,
        sex:null,
        password:null,
        confirmPwd:null,
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {

    },
    submit(e){
        console.log(e)
        //校验
        if(e.detail.value.nickName.length==0){
            wx.showToast({
              title: '昵称不能为空',
              icon:'error'
            })
            return;
        }
        if(e.detail.value.userName.length==0){
            wx.showToast({
              title: '用户名不能为空',
              icon:'error'
            })
            return;
        }
        if(e.detail.value.phonenumber.length==0){
            wx.showToast({
              title: '手机号不能为空',
              icon:'error'
            })
            return;
        }
        if(e.detail.value.sex.length==0){
            wx.showToast({
              title: '性别不能为空',
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
        if(e.detail.value.password!=e.detail.value.confirmPwd){
            wx.showToast({
              title: '密码不一致',
              icon:'error'
            })
            return;
        }
        //注册
        request('/register','POST',{
            isToken: false
        },{
            nickName:e.detail.value.nickName,
            username:e.detail.value.userName,
            phonenumber:e.detail.value.phonenumber,
            sex:e.detail.value.sex=='男'?0:1,
            password:e.detail.value.password,
        }).then(res=>{
            console.log(res)
            if(res.code===200){
                wx.showToast({
                    title: '注册成功',
                    icon:'success'
                })
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