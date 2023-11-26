<script>
    function showAttendanceAlert() {
        alert("Attendance submitted successfully!");
    }

    function updateDigitalClock() {
        var now = new Date();
        var hours = now.getHours();
        var minutes = now.getMinutes();
        var seconds = now.getSeconds();

        // Ensure leading zeros for single-digit minutes and seconds
        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        var formattedTime = hours + ":" + minutes + ":" + seconds;
        document.getElementById('currentTime').innerText = formattedTime;
    }

    setInterval(updateDigitalClock, 1000);

    function searchTable() {
        var input, filter, table, tr, td, i, txtValue;
        input = document.getElementById('searchInput');
        filter = input.value.toUpperCase();
        table = document.querySelector('table');
        tr = table.querySelectorAll('tr');
        for (i = 1; i < tr.length; i++) {  // Start from 1 to skip the header row
            tds = tr[i].querySelectorAll('td');
            for (j = 0; j < tds.length; j++) {
                td = tds[j];
                if (td) {
                    txtValue = td.textContent || td.innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = '';
                        break;
                    } else {
                        tr[i].style.display = 'none';
                    }
                }
            }
        }
    }

    document.getElementById('searchInput').addEventListener('input', searchTable);
</script>
