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
		    var volumns = [];
		    for (var i = 0; i < rawData.length; i++) {
		        categoryData=rawData[i][0];
		        volumns.push(rawData[i][1]);
		        values.push(rawData[i][2]);
		    }
		    return {
		        categoryData: categoryData,
		        values: values,
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
				
				var myChart = echarts.init(document.getElementById('main'));
				// 指定图表的配置项和数据
				var option = {
					    title: {
					    	text: '项目starred趋势图',
					        subtext: '数据来自github.com'
					    },
					    tooltip: {
					        trigger: 'axis'
					    },
					    legend: {
					        data:[rowData.categoryData]
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
					            data: rowData.values
					        }
					    ]
					};

				// 使用刚指定的配置项和数据显示图表。
				myChart.setOption(option);
			},
			error:function(){
				alert('request api fail!');
			}
		});
	});
});