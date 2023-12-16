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


    function viewAttendance(employeeId) {

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
