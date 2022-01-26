const btn = window.document.querySelector(".login");
const password_input = document.querySelector("#password");
const username_input = window.document.querySelector("#username")

btn.addEventListener("click", async (e) => {
    e.preventDefault()
    const username = username_input.value;
    const password = password_input.value;

    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    let raw = JSON.stringify({
        username,
        password
    });

    let requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    let data = null;
    let response = null
    try {
        response = await fetch("http://localhost:8888/api/v1/login", requestOptions)
        data = await response.json();
    } catch (e) {
        console.log(e)
    }

    localStorage.setItem("user", JSON.stringify(data))
    if (data) {
        switch (data["clientType"]) {
            case "Person":
                window.location.href = "http://localhost:8888/html/dashboard/dashboardClient.html";
                break;
            // case "Company":
            //     window.location.href = "http://localhost:8888/html/dashboardCompany";
            //     break;
            case "Employee":
                window.location.href = "http://localhost:8888/html/dashboard/dashboardEmployee.html";
                break;
            default:
                break;
        }
    } else {
        if (response.status === 204) {
            password_input.style.borderColor = "red";
            const error_message = document.querySelector("#wrong_password");
            error_message.style.display = "inline";
        } else {
            window.alert("Something went wrong")
        }
    }
});

password_input.addEventListener("change", () => {
        if (document.querySelector("#wrong_password").style.display !== null) {
            document.querySelector("#wrong_password").style.display = 'none';
        }
    }
)