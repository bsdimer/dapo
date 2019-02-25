//Team chart
    var ctx = document.getElementById("team-chart");
    ctx.height = 150;
    var myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: ["1 July", "1 Aug", "1 Sep", "1 Oct", "1 Nov", "1 Dec", "1 Jan", "1 Feb"],
            type: 'line',
            defaultFontFamily: 'Roboto',
            datasets: [{
                data: [0, 0.2, 0.6, 0.3, 0, 1, 0.9, 0],
                label: "",
                backgroundColor: 'transparent',
                borderColor: '#29abff',
                borderWidth: 2,
                pointStyle: 'circle',
                pointRadius: 3,
                pointBorderColor: '#29abff',
                pointBackgroundColor: '#29abff',
                    }, {
              
                data: [0, 0.4, 0.6, 0.2, 1, 0.5, 0.9, 0],
				label: "",
                backgroundColor: 'transparent',
                borderColor: '#b7bdc1 ',
                borderWidth: 2,
                pointStyle: 'circle',
                pointRadius: 3,
                pointBorderColor: '#b7bdc1',
                pointBackgroundColor: '#b7bdc1',
                    }] 
        },
        options: {
            responsive: true,
            tooltips: {
                mode: 'index',
                titleFontSize: 12,
                titleFontColor: '#111',
                bodyFontColor: '#111',
                backgroundColor: '#fff',
                titleFontFamily: 'Roboto',
                bodyFontFamily: 'Roboto',
                cornerRadius: 3,
                intersect: true,
            },
            legend: {
                position: 'top',
                labels: {
                    usePointStyle: true,
                    fontFamily: 'Roboto',
					color: '#fff',
                },


            },
            scales: {
                xAxes: [{
                    display: true,
                    gridLines: {
                        display: false,
                        drawBorder: false
                    },
                    scaleLabel: {
                        display: false,
                        labelString: 'Month'
                    }
                        }],
                yAxes: [{
                    display: true,
                    gridLines: {
                        display: false,
                        drawBorder: false
                    },
                    scaleLabel: {
                        display: true,
                        labelString: '$ square feet*'
                    }
                        }]
            },
            title: {
                display: false,
            }
        }
    });