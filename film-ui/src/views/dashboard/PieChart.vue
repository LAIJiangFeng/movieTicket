<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import * as echarts from 'echarts';
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'
import { getFilmTypeCount } from "@/api/film/film";
export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '300px'
    }
  },
  data() {
    return {
      chart: null
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.initChart()
    })
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initChart() {
      this.chart = echarts.init(this.$el, 'macarons')
      getFilmTypeCount().then(res=>{
        this.chart.setOption({
          title: {
            text: '电影分类统计',
            left: 'left',
            textStyle: {
              fontSize:14
            }
          },
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
          },
          grid: {
            top: 30,
          },
          legend: {
            left: 'center',
            bottom: '10',
            // data: ['Industries', 'Technology', 'Forex', 'Gold', 'Forecasts']
          },
          series: [
            {
              name: '电影分类统计',
              type: 'pie',
              roseType: 'radius',
              radius: [15, 95],
              center: ['50%', '38%'],
              data: res,
              animationEasing: 'cubicInOut',
              animationDuration: 2600
            }
          ]
        })
      })
    }
  }
}
</script>
