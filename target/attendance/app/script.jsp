<script>
     function showAttendanceAlert() {
            alert("You have submitted an attendance");
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
    function viewAttendance(employeeId) {
        console.log("Viewing attendance for employee with ID: " + employeeId);

        window.location.href = "./employeeReport?employeeId=" + employeeId;
    }
 function editEmployee(employeeId) {
     $.ajax({
         url: "./updateEmployee",
         data: { employeeId: employeeId },
         type: "GET",
         success: function (data) {
             // Populate the modal with the fetched data
             $(".modal-content").html(data);
             // Show the modal
             $(".modal").show();
         },
         error: function (xhr, status, error) {
             console.error("AJAX request failed:", status, error);
             alert("Failed to fetch employee data. Please try again.");
         }
     });
 }


 // Function to close the modal
 function closeModal() {
     $(".modal").hide();
 }


    function typeDropDown(selectedValue) {
        console.log("Selected value:", selectedValue);

        var singleDatePicker = document.getElementById('singleDatePicker');
        var rangeDatePicker = document.getElementById('rangeDatePicker');

        if (selectedValue === '2') {
            singleDatePicker.style.display = 'block';
            rangeDatePicker.style.display = 'none';
        } else if (selectedValue === '3') {
            singleDatePicker.style.display = 'none';
            rangeDatePicker.style.display = 'block';
        } else {
            singleDatePicker.style.display = 'none';
            rangeDatePicker.style.display = 'none';
        }
        function addLog(message) {
            var logsDiv = document.getElementById("logs");
            var logParagraph = document.createElement("p");
            logParagraph.textContent = message;
            logsDiv.appendChild(logParagraph);
        }


    }
</script>
