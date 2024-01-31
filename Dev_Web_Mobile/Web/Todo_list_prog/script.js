
function addTodo() {
  const todoList = document.getElementById('todo-list');
  const todoInput = document.getElementById('todo-input');
  const taskText = todoInput.value.trim();

  if (taskText) {
    const todoItem = document.createElement('li');
    todoItem.classList.add('todo-item');
    todoItem.innerHTML = `
      <span onclick="editTask(this)">${taskText}</span>
      <span class="todo-delete" onclick="deleteTodo(this)">x</span>
    `;

    todoList.appendChild(todoItem);
    todoInput.value = '';
    todoInput.focus();
  }
}

function editTask(element) {
  let newText = prompt('Edit your task:', element.textContent);
  if (newText !== null && newText.trim() !== '') {
    element.textContent = newText.trim();
  }
}

function deleteTodo(element) {
  element.parentElement.remove();
}

document.getElementById('todo-input').addEventListener('keypress', function(event) {
  if (event.key === 'Enter') {
    addTodo();
  }
});
