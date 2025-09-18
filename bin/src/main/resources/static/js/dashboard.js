// ============================
// Toggle Admin Name (if adminAvatar exists)
const avatar = document.getElementById("adminAvatar");
if (avatar) {
    avatar.addEventListener("click", function () {
        const adminName = document.getElementById("adminName");
        adminName.innerText = adminName.innerText === "Surya" ? "Admin User" : "Surya";
    });
}

// ============================
// Theme Toggle (Persistent)
// ============================
document.addEventListener("DOMContentLoaded", () => {
    // Apply saved theme
    const savedTheme = localStorage.getItem("theme");
    if (savedTheme === "light") document.body.classList.add("light");

    // Update button text
    const toggleBtn = document.getElementById("themeToggle");
    if (toggleBtn) {
        toggleBtn.textContent = document.body.classList.contains("light") ? "☀️" : "🌙";

        // Toggle button event
        toggleBtn.addEventListener("click", () => {
            document.body.classList.toggle("light");
            const isLight = document.body.classList.contains("light");
            localStorage.setItem("theme", isLight ? "light" : "dark");
            toggleBtn.textContent = isLight ? "☀️" : "🌙";
        });
    }
});

// ============================
// Chart.js example (if salesChart exists)
const chartCanvas = document.getElementById('salesChart');
if (chartCanvas) {
    const ctx = chartCanvas.getContext('2d');
    new Chart(ctx, {
        type: 'line',
        data: {
            labels: ['Jan','Feb','Mar','Apr','May','Jun'],
            datasets: [{
                label: 'Sales',
                data: [120,190,300,500,200,300],
                borderColor: '#3b82f6',
                backgroundColor: 'rgba(59,130,246,0.2)',
                fill: true,
                tension: 0.3
            }]
        }
    });
}
