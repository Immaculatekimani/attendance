<script>
// ATTENDANCE CONFIRMATION
     function showAttendanceAlert() {
            alert("You have submitted an attendance");
     }

// ATTENDANCE DIGITAL CLOCK
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

        window.location.href = "./employeeReport?employeeId=" + employeeId; // TO REDIRECT TO A SINGLE EMPLOYEE ATTENDANCE
    }

//EDIT EMPLOYEE POP-UP FORM
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

document.getElementById("openPopup").addEventListener("click", function() {
        document.getElementById("popupForm").style.display = "block";
    });

    document.getElementById("closePopup").addEventListener("click", function() {
        document.getElementById("popupForm").style.display = "none";
    });

    window.addEventListener("click", function(event) {
        if (event.target == document.getElementById("popupForm")) {
            document.getElementById("popupForm").style.display = "none";
        }
    });


// REPORTS FILTER DROPDOWNS

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


    }

       // ADD CONTENT TO THE LOG DIVS

        function addLog(message) {
            var logsDiv = document.getElementById("logs");
            var logParagraph = document.createElement("p");
            logParagraph.textContent = message;
            logsDiv.appendChild(logParagraph);
        }

    //VALIDATIONS FOR LOGIN AND REGISTER

    function validateLoginForm() {
        var username = document.getElementById('username').value;
        var password = document.getElementById('password').value;

        if (username.trim() === '' || password.trim() === '') {
            alert('Username and password are required');
            return false;
        }

        return true;
    }

    function validateRegistrationForm() {
       var username = document.getElementById('register-username').value;
       var password = document.getElementById('register-password').value;
       var confirmPassword = document.getElementById('confirmPassword').value;

       if (username.trim() === '' || password.trim() === '' || confirmPassword.trim() === '') {
           alert('All fields are required');
           return false;
       }

       if (password !== confirmPassword) {
           alert('Passwords do not match');
           return false;
       }

       // Password complexity requirements
       if (password.length < 8) {
           alert('Password must be at least 8 characters long');
           return false;
       }


       var passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]+$/;
       if (!passwordRegex.test(password)) {
           alert('Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character');
           return false;
       }

        // check if user exists
         $.get('./user', { username: username })
               .done(function(data) {
                   if (data.exists) {
                       alert('Username already exists. Please choose a different username.');
                   } else {
                       document.forms[1].submit(); // Submit the form if the username is available
                   }
               })
               .fail(function(error) {
                   console.error('Error during username availability check:', error);
               });

           return false;
     }


    function showRegisterForm() {
        document.getElementById('login-form').style.display = 'none';
        document.getElementById('register-form').style.display = 'block';
    }

    function showLoginForm() {
        document.getElementById('register-form').style.display = 'none';
        document.getElementById('login-form').style.display = 'block';
    }

</script>
