document.addEventListener('DOMContentLoaded', () => {
    // Form validation for add/edit employee
    const forms = document.querySelectorAll('form');
    forms.forEach(form => {
        form.addEventListener('submit', (event) => {
            const nameInput = form.querySelector('input[name="name"]');
            const salaryInput = form.querySelector('input[name="salary"]');

            if (!nameInput.value.trim()) {
                alert('Please enter a valid employee name.');
                event.preventDefault();
                return;
            }

            if (!salaryInput.value || salaryInput.value <= 0) {
                alert('Please enter a valid salary greater than 0.');
                event.preventDefault();
                return;
            }
        });
    });

    // Confirmation for delete action
    const deleteButtons = document.querySelectorAll('.btn-delete');
    deleteButtons.forEach(button => {
        button.addEventListener('click', (event) => {
            if (!confirm('Are you sure you want to delete this employee?')) {
                event.preventDefault();
            }
        });
    });
});