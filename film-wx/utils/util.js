const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return `${[year, month, day].map(formatNumber).join('-')}`
  // ${[hour, minute, second].map(formatNumber).join(':')}
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : `0${n}`
}
const formatTime2 = (date,x) => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1-x
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return `${[year, month, day].map(formatNumber).join('-')}`
  // ${[hour, minute, second].map(formatNumber).join(':')}
}

/**
* 参数处理
* @param {*} params  参数
*/
const tansParams=(params)=> {
  console.log(params)
  let result = ''
  for (const propName of Object.keys(params)) {
    const value = params[propName];
    var part = encodeURIComponent(propName) + "=";
    if (value !== null && typeof (value) !== "undefined") {
      if (typeof value === 'object') {
        for (const key of Object.keys(value)) {
          if (value[key] !== null && typeof (value[key]) !== 'undefined') {
            let params = propName + '[' + key + ']';
            var subPart = encodeURIComponent(params) + "=";
            result += subPart + encodeURIComponent(value[key]) + "&";
          }
        }
      } else {
        result += part + encodeURIComponent(value) + "&";
      }
    }
  }
  return result
}

module.exports = {
  formatTime,
  formatTime2,
  tansParams,
}
