function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('.picture-src').attr('src', e.target.result);
		};

		reader.readAsDataURL(input.files[0]);
	}
}

function getFinTrendChart() {
	finChart = document.getElementById("myAreaChart");
	if (finChart != null) {
		ctx = finChart.getContext('2d');
		Chart.defaults.global.defaultFontColor = '#fff';
		var myLineChart = new Chart(ctx, {
			type : 'line',
			data : {
				labels : [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul' ],
				datasets : [ {
					label : 'Savings',
					data : [ 5000, 10000, 45000, 25000, 1000, 10000, 0 ],
					borderColor : "#1cc88a",
					fill : false
				}, {
					label : 'Contributions',
					data : [ 15000, 15000, 20000, 15000, 20000, 25000, 15000 ],
					borderColor : "#f6c23e",
					fill : false
				}, {
					label : 'Loan Balance',
					data : [ 0, 0, 0, 100000, 0, 75000, 65000 ],
					borderColor : "#36b9cc",
					fill : false
				} ]
			}
		});
	}
}

function getCashFlowOVWChart(id) {
	ctx = document.getElementById("chart-area-" + id);
	if (ctx != null) {
		Chart.defaults.global.defaultFontColor = '#fff';
		var myBarChartGrp = new Chart(ctx, {
			type : 'bar',
			data : {
				labels : [ "Jan", "Feb", "Mar"],
				datasets : [ {
					label : "Inflow",
					backgroundColor : "#1cc88a",
					data : [ 133, 221, 321 ]
				}, {
					label : "Outflow",
					backgroundColor : "#36b9cc",
					data : [ 408, 547, 123 ]
				} ]
			},
			options : {
				title : {
					display : true,
					text : 'Amount (thousands)'
				}
			}
		});
	}
}

getFinTrendChart();