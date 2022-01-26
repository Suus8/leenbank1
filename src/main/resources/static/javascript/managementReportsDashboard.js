/* ---- Page last refreshed script --- */

function updateLastRefreshDateTime() {
    let now = new Date();
    let date = now.toLocaleDateString()
    let time = now.toLocaleTimeString()

    document.querySelector("#last-refresh-date").append(date)
    document.querySelector("#last-refresh-time").append(time)

}

updateLastRefreshDateTime()

function balanceInCentsToEuro(balanceValueInCents) {
    return "€ " + (balanceValueInCents * 0.01).toLocaleString(undefined, {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    })
}

/* ---- Fetch JSON data method --- */
async function fetchData(URL) {
    let requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };
    const response = await fetch(URL, requestOptions)
    return await response.json()
}

/* ---- Balance Top 10 script --- */
const balanceTopTenConsumerTableBody = document.querySelector("#balance-top-ten-consumer-table-body")
const balanceTopTenBusinessTableBody = document.querySelector("#balance-top-ten-business-table-body")
const transactionsTopTenTableBody = document.querySelector("#transactions-top-ten-table-body")
const topTenBalanceConsumerEndpointURL = "http://localhost:8888/topTenBalance/CONSUMER"
const topTenBalanceBusinessEndpointURL = "http://localhost:8888/topTenBalance/SMALL_BUSINESS"
const topTenNumberOfTransactionsEndpointURL = "http://localhost:8888/topTenNumberOfTransactions"

async function createTopTenTable(URL, table, jsonKeys) {
    let data = await fetchData(URL);

    let positionCounter = 1
    data.forEach(obj => {
        const tr = document.createElement("tr");

        const topTenPosition = document.createElement("td")
        topTenPosition.innerText = positionCounter.toString()

        const column2Value = document.createElement("td")
        column2Value.innerText = obj[jsonKeys[0]]

        const column3Value = document.createElement("td")
        let value = obj[jsonKeys[1]]
        if (jsonKeys[1] === "accountBalanceInCents") {
            column3Value.innerText = balanceInCentsToEuro(value)
        } else {
            column3Value.innerText = value
        }

        const column4Value = document.createElement("td")
        column4Value.innerText = obj[jsonKeys[2]]

        tr.appendChild(topTenPosition)
        tr.appendChild(column2Value)
        tr.appendChild(column3Value)
        tr.appendChild(column4Value)

        table.appendChild(tr)
        positionCounter++
    })
}

const balanceTopTenJsonKeys = ["clientName", "accountBalanceInCents", "accountIBAN"]
const transactionTopTenJsonKeys = ["clientName", "numberOfTransactions", "clientId"]
createTopTenTable(topTenBalanceConsumerEndpointURL, balanceTopTenConsumerTableBody, balanceTopTenJsonKeys)
createTopTenTable(topTenBalanceBusinessEndpointURL, balanceTopTenBusinessTableBody, balanceTopTenJsonKeys)
createTopTenTable(topTenNumberOfTransactionsEndpointURL, transactionsTopTenTableBody, transactionTopTenJsonKeys)

/* ---- Bar chart script --- */
const averageBalancePerSectorEndpointURL = "http://localhost:8888/averageBalancePerSector"
let companySectors = []
let averageBalanceData = []

async function getSectorsAndBalanceData() {
    let data = await fetchData(averageBalancePerSectorEndpointURL);

    data.forEach(obj => {
        let companySector = obj["companySector"]
        companySectors.push(companySector)
        let balanceInCents = obj["accountBalanceInCents"]
        let balanceInEuro = (balanceInCents / 100).toFixed(2)
        averageBalanceData.push(balanceInEuro)
    })
}

async function makeBarChart() {
    await getSectorsAndBalanceData()

    $(function () {

        let chartData = {
            labels: companySectors,
            datasets: [
                {
                    label: 'Average Account Balance',
                    backgroundColor: 'rgba(60,141,188,0.9)',
                    borderColor: 'rgba(60,141,188,0.8)',
                    pointRadius: false,
                    pointColor: '#3b8bba',
                    pointStrokeColor: 'rgba(60,141,188,1)',
                    pointHighlightFill: '#fff',
                    pointHighlightStroke: 'rgba(60,141,188,1)',
                    data: averageBalanceData
                }
            ]
        }

        let barChartCanvas = $('#barChart').get(0).getContext('2d')
        let barChartData = $.extend(true, {}, chartData)
        barChartData.datasets[0] = chartData.datasets[0]

        let barChartOptions = {
            legend: {
                display: false
            },
            responsive: true,
            maintainAspectRatio: false,
            datasetFill: false,
            tooltips: {
                callbacks: {
                    label: function (tooltipItems) {
                        // return "€ " + tooltipItems.yLabel.toFixed(2).toString().replace(".", ",")
                        return "€ " + tooltipItems.yLabel.toLocaleString();
                    }
                }
            },
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true,
                        // Include a dollar sign in the ticks
                        callback: function (value) {
                            return '€ ' + value;
                        }
                    }
                }]
            }
        }

        new Chart(barChartCanvas, {
            type: 'bar',
            data: barChartData,
            options: barChartOptions
        })
    })
}

makeBarChart()


/* ---- Data table script --- */
const allAccountsEndpointURL = "http://localhost:8888/allAccounts"
const allAccountsTableBody = document.querySelector("#dto-table-body")

async function createAllAccountsTable() {
    let data = await fetchData(allAccountsEndpointURL);

    data.forEach(obj => {

        const tr = document.createElement("tr");

        const accountType = document.createElement("td")
        accountType.innerText = obj["accountType"]

        const companySector = document.createElement("td")
        companySector.innerText = obj["companySector"]

        const clientName = document.createElement("td")
        clientName.innerText = obj["clientName"]

        const iban = document.createElement("td")
        iban.innerText = obj["accountIBAN"]

        const balance = document.createElement("td")
        let balanceValueInCents = obj["accountBalanceInCents"].toString()
        balance.innerText = "€ " + balanceValueInCents.slice(0, -2) + "," + balanceValueInCents.slice(-2)

        tr.appendChild(accountType)
        tr.appendChild(companySector)
        tr.appendChild(clientName)
        tr.appendChild(iban)
        tr.appendChild(balance)

        allAccountsTableBody.appendChild(tr)

    })

    // see https://datatables.net/manual/ for documentation
    $(function () {
            $("#data-table1").DataTable({
                "order": [4, 'desc'], // sets the default index of the column to sort and 'asc' or 'desc'
                // translate the default english text of the elements to dutch
                "language": {
                    "info": "_START_ t/m _END_ van _TOTAL_ records",
                    "paginate": {
                        "next": "Volgende",
                        "previous": "Vorige"
                    },
                    "buttons": {
                        "colvis": "Toon kolommen",
                        "copy": "Kopieer"
                    },
                    "search": "Zoek:"
                },
                "responsive": true, "lengthChange": false, "autoWidth": false,
                // declare which tool buttons to show
                "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"],
                orderCellsTop: true,
                fixedHeader: true,

                // add filter to the bottom of each column
                initComplete: function () {
                    this.api().columns().every(function () {
                        let column = this;
                        let select = $('<select><option value=""></option></select>')
                            .appendTo($(column.footer()).empty())
                            .on('change', function () {
                                let val = $.fn.dataTable.util.escapeRegex($(this).val());
                                column
                                    .search(val ? '^' + val + '$' : '', true, false)
                                    .draw();
                            });
                        column.data().unique().sort().each(function (d) {
                            select.append('<option value="' + d + '">' + d + '</option>')
                        });
                    });
                }

            }).buttons().container().appendTo('#data-table1_wrapper .col-md-6:eq(0)');
        }
    )
}

createAllAccountsTable()


