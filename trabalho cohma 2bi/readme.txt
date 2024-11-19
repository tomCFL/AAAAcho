========comandos para o postman============
nova tarefa--------------------------------
POST http://localhost:8080/tasks

todas as tarefas---------------------------
GET http://localhost:8080/tasks

editar uma tarefa (sempre que editado volta para a lsita de a fazer)-----------------------------------
obs: o postman mostra a createdDate como null apos edição mas eu olhei direto no jason e lá apos a 
modificação ele continua normal, então acho que é só algum bug do postman? sla talvez seja meu pc
PUT http://localhost:8080/tasks/{id}

mudar status da tarefa (A Fazer → Em Progresso → Concluído)----------------------
PUT http://localhost:8080/tasks/{id}/move

ordenar tarefa por prioridade em coluna (automatico) -------------------------
mas para msotrar apenas a coluna especifica 
GET http://localhost:8080/tasks/status/{status}   (os {status} são /A Fazer, /Concluído e /Em Progresso   )

filtrar tarefas por prioridade de data limite-------------------------
GET http://localhost:8080/tasks/priority/{priority}/limitDate/{limitDate}   ex: priority/alta/limitDate/2024-11-18T14:40:15

Gerar um relatório---------------------------------------------------
GET http://localhost:8080/tasks/report

===================authenticação===============================
o codico ta aparentemente certo mas meu maven ta quebrado e não reconehce a dependencia direito aparentemente?
obs2: corrige com dó ;-; passei 6 horas tentando fazer mas acho que meu intellij ta só quebrado 
