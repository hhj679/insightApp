/**
 * 
 */

$(document).ready(function(){
	var iskeydown = false;
	$("#kw").on('keydown',function(e){
		if(e.keyCode==13){
			var val = $("#kw").val();
			if(val=='' || val==undefined){
				window.location.reload();
			} else {
				$('#su').click();
			}
		}
	});

	$('#su').click(function(){
		if(!iskeydown) {
			$('#lg').hide();
			var result_logo = $('#result_logo');
			result_logo.css('float', 'left');
			result_logo.css('margin', '7px 0 0');
			result_logo.show();

			var fm = $('#form');
			fm.css('clear', 'none');
			fm.css('float', 'left');
			fm.css('margin', '11px 0 0 10px');
			fm.css('position', 'relative');

			$('#head').css('min-height', '0');
			$('#head .head_wrapper').css('height', '50px');
			$('.s_form').css('top', '0');
			$('.s_form_wrapper').css('top', '0');
			
			$('#main').show();

			iskeydown = true;
		}
		
		function splitData(rawData) {
		    var categoryData = '';
		    var values = [];
		    var mvalues = [];
		    var volumns = [];
		    for (var i = 0; i < rawData.length; i++) {
		        categoryData=rawData[i][0];
		        volumns.push(rawData[i][1]);
		        values.push(rawData[i][2]);
		        if(i>0) {
		        	mvalues.push(rawData[i][2] + mvalues[i-1]);
		        } else {
		        	mvalues.push(rawData[i][2]);
		        }
		    }
		    return {
		        categoryData: categoryData,
		        values: values,
		        mvalues: mvalues,
		        volumns: volumns
		    };
		}
		
		$.ajax({
			type: 'GET',
			url: 'starred/' + $("#kw").val().replace('/', 'ppp%3B%3B%3B'),
			dataType: 'json',
			success:function(data){
				console.log(data);
				var rowData = splitData(data);
				
				// 指定图表的配置项和数据
				var common_option = {
					    title: {
					    	text: '项目starrd升涨趋势',
					        subtext: '数据来自github.com'
					    },
					    tooltip: {
					        trigger: 'axis'
					    },
					    legend: {
					    	align: "right", //程度标的目标地位
					    	orient: "horizontal", //垂直标的目标地位
					        data:[rowData.categoryData/*, rowData.categoryData + '月Starred数'*/]
					    },
					    toolbox: {
					        show: true,
					        feature: {
					            dataZoom: {
					                yAxisIndex: 'none'
					            },
					            dataView: {readOnly: false},
					            magicType: {type: ['line', 'bar']},
					            restore: {},
					            saveAsImage: {}
					        }
					    },
					    xAxis:  {
					        type: 'category',
					        boundaryGap: false,
					        data: rowData.volumns
					    },
					    yAxis: {
					        type: 'value',
					        axisLabel: {
					            formatter: '{value} 人'
					        }
					    },
					    series: [
					        {
					            name: rowData.categoryData,
					            type:'line',
					            data: rowData.mvalues
					        }
					    ]
					};

				//Starrd 升涨趋势
				var option1 = common_option;
				var myChart1 = echarts.init(document.getElementById('chart1'));
				myChart1.setOption(option1);
				
				//月Starred数
				var option2 = common_option;
				option2.title.text = ['项目月Starrd数'];
				option2.series[0] = {
					name: rowData.categoryData,
					type : 'line',
					data: rowData.values
				};
				var myChart2 = echarts.init(document.getElementById('chart2'));
				myChart2.setOption(option2);
			},
			error:function(){
				alert('request api fail!');
			}
		});
	});
});