$(function() {
	loadStatistic();
	
	function loadStatistic() {
		$.ajax({
		    method: 'GET',
			dataType: 'json',
		    contentType: 'application/json',
		    url: window.location.pathname + '/statistic',
		}).done(function(data) {
			initDonut(data.ordersStatistic);
			initBar(data.salesStatistic);
	    });
	}
	
	function initDonut(donutData) {
	    Morris.Donut({
	        element: 'morris-donut-chart',
	        data: [{
	            label: "Waiting orders",
	            value: donutData.WAITING
	        }, {
	            label: "Aceppted orders",
	            value: donutData.ACCEPT
	        }, {
	            label: "Reject orders",
	            value: donutData.REJECT
	        }, {
	            label: "Cancel orders",
	            value: donutData.CANCEL
	        }],
	        resize: true
	    });	
	}
	
	function initBar(barData) {
		var data = []; 
		for(var i = 0; i < barData.length; i++)
			data.push({month: i + 1, newSale: barData[i][0], oldSale: barData[i][1]});
		
		Morris.Bar({
			element: 'morris-bar-chart',
			data: data,
			xkey: 'month',
			ykeys: ['newSale', 'oldSale'],
			labels: ['New Sale', 'Old Sale'],
			hideHover: 'auto',
			resize: true
		});
	}
	
});
