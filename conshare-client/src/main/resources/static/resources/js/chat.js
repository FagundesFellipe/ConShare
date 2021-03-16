var MeuNome='';

// Aqui o sistema pega a altura da tela (document) e passa esta informação para o objeto "body". 
// Isto é necessário para centralizar verticalmente o container principal
$('body').css('height', $(document).height() + 'px');
$('.container').fadeIn();

// Aqui o sistema passa para todos os objetos do tipo "input" uma vuncao que monitora o pressionamento de teclas
// Quando a tecla pressionada for "Enter" (13) ele dispara o evento click do botão associado 
$('input[type=text]').keypress(function (e) {
    var k = e.keyCode || e.wich;
    if (k == 13) {
        $(this).parent().find('input[type=button]').click();
    }
})

// Aqui o sistema passa o foco para o objeto input relacionado ao nome de usuario
$('#txtusername').focus();

// Funcao de autenticacao de usuarios
function Autenticar() {
    // Primeiro o sistema verifica se algo foi digitado no campo específico
    if ($('#txtusername').val().trim() == '') {
        alert('Informe o nome de usuário a ser utilizado no chat!');
        $('#txtusername').focus();
        return;
    }
    // Em seguida o sistema envia a informacao ao servidor e pede para ser aceito
    // A informacao enviada consiste em um atributo "0", que representa o pedido de conexao, seguida do nome sugerido. As informacoes sao separadas pelo caracter |
    socket.send('0|'+$('#txtusername').val().trim());
}

function enviarMensagem() {
    // Esta funcao e responsavel por enviar mensagens ao servidor
    // O primeiro caracter sempre deve ser o número "1", que representa uma mensagem simples
    // Em seguia devem ser enviadas os demais parametros, sempre separados pelo caracter |
    // As informaçoes são as seguintes
    // tipo | Quem envia | Para quem enviou | Mensagem

    // Somente enviar mensagens não vazias

    if ($('#txtmessage').val().trim()=='')return;

    socket.send('1|'+MeuNome+'|'+$('.usersList').find('.userSelected').html()+'|'+$('#txtmessage').val().trim());

    // Logo abaixo o sistema adiciona na lista de chat as informacoes de hora, nome de quem enviou e a mensagem enviada
    let newMessage=document.createElement('div');
    let data=new Date;
    data=data.getHours()+':'+data.getMinutes()+':'+data.getSeconds();        
    $(newMessage).addClass('message').html('<b>'+data+'<br>"'+MeuNome+'" disse:</b> '+$('#txtmessage').val().trim());
    $('.chat').append(newMessage);
    // Na linha abaixo o sistema apaga a mensagem do "input", preparando-o para novas mensagens
    $('#txtmessage').val('');

    // Em seguida rolar o chat até o final
    $('.chat').prop("scrollTop", $('.chat').prop("scrollHeight"));
}

// Logo abaixo o sistema intancia um novo objeto com base na classe WebSocket, apontando-o para o servidor correto
//var socket = new WebSocket("ws://localhost:8089");
var socket = new WebSocket("ws://gwbm.com.br:8089/chat/server");

// Logo abaixo são tratados os enventos mais importantes o objeto "socket"

// Aqui é tratado o evento de abertura de conexao
socket.onopen = function (e) {
    // Aqui o sistema muda a informação do container de status de conexao
    $('.status').html('<b>Status da conexão: </b>conexão estabelecida');
};

// Aqui abaixo o sistema trata o evento principal do objeto "socket", no caso, o recebimento de mensagens. 
// E passado um parametro "event" que contera uma propriedade "data", sendo que esta por sua vez contem a propria mensagem recebida
socket.onmessage = function (event) {

    // Aqui o sistema tranforma a mensagem recebida em um array de informações. O delimitador é o caracter |
    let d=event.data.split('|');
    let tipo=d[0];
    let nome=d[1];
    let mensagem=d[2];

    // O tipo -1 recebido se refere a informacao de usuario duplicado
    if (tipo=='-1'){
        alert('Já existe um usuário conectado com este mesmo nome!');
        return
    }

    // O tipo 1 representa o aceite de conexao
    if (tipo=='1'){
        // Aqui o sistema oculta o container de autenticacao e exibe o de mensagens
        $('.sendMessage').find('span').html('<b>Conectado como: </b>'+$('#txtusername').val());    
        $('.autenticate').fadeOut(function(){
            $('.sendMessage').fadeIn('', function () {
                $(this).find('input[type=text]').focus();            
            });            
        });

        // Em seguida o sistema atribui a variavel global "MeuNome" o nome sugerido para conexao e aceita pelo servidor
        MeuNome=$('#txtusername').val();

        // Uma nova linha é inserida no chat informando que o usuario atual conectou
        let newMessage=document.createElement('div');
        let data=new Date;
        data=data.getHours()+':'+data.getMinutes()+':'+data.getSeconds();
        $(newMessage).addClass('message').html('<b>'+data+'<br>"'+MeuNome+'" entrou no chat</b>');
        $('.chat').append(newMessage);
    }

    // O tipo 2 se refere a informacoes de lista de usuarios conectados no momento da conexao do usuario atual
    if (tipo=='2'){
        let newUser=document.createElement('li');
        $(newUser).html(nome);
        // Sempre que este usuario (li) for clicado o sistema deve desmaracar os demais e em seguida marca-lo, definindo assim para quem sera enviada a mensagem
        $(newUser).on('click', function () {
            $('.usersList').find('li').removeClass('userSelected');
            $(this).addClass('userSelected');
        })        
        $('.usersList').find('ul').append(newUser);
    }    

    // O tipo 3 se refere a informacao vinda do servidor de um novo usuario que se conectou
    if (tipo=='3'){
        let newUser=document.createElement('li');
        $(newUser).html(nome);
        // Sempre que este usuario (li) for clicado o sistema deve desmaracar os demais e em seguida marca-lo, definindo assim para quem sera enviada a mensagem
        $(newUser).on('click', function () {
            $('.usersList').find('li').removeClass('userSelected');
            $(this).addClass('userSelected');
        })        
        $('.usersList').find('ul').append(newUser);

        let newMessage=document.createElement('div');
        let data=new Date;
        data=data.getHours()+':'+data.getMinutes()+':'+data.getSeconds();
        $(newMessage).addClass('message').html('<b>'+data+'<br>"'+nome+'" entrou no chat</b>');
        $('.chat').append(newMessage);
    }    

    // O tipo 4 se refere a mensagens comuns
    if (tipo=='4'){
        let newMessage=document.createElement('div');
        let data=new Date;
        data=data.getHours()+':'+data.getMinutes()+':'+data.getSeconds();        
        $(newMessage).addClass('message').html('<b>'+data+'<br>"'+nome+'" disse:</b> '+mensagem);
        $('.chat').append(newMessage);

    }

    // O tipo 9 se refere a um usuario que desconectou
    if (tipo=='9'){
        let u=$('.usersList').find('li');
        for (let i=0;i<$(u).length;i++){
            if ($(u[i]).html()==nome)$(u[i]).remove();
        }
        let newMessage=document.createElement('div');
        let data=new Date;
        data=data.getHours()+':'+data.getMinutes()+':'+data.getSeconds();
        $(newMessage).addClass('message').html('<b>'+data+'<br>"'+nome+'" saiu do chat</b>');
        $('.chat').append(newMessage);
    }

    // Ao final de qualquer mensagem sempre rolar o chat ate o final
    $('.chat').prop("scrollTop", $('.chat').prop("scrollHeight"));
};

// Aqui o sistema trata eventos de fechamento de conexao
socket.onclose = function (event) {
    if (event.wasClean) {
        $('.status').html('<b>Status da conexão: </b>conexão encerrada');
    } else {
        $('.status').html('<b>Status da conexão: </b>conexão perdida');
    }
};

// Aqui o sistema trata eventos de erros de conexao
socket.onerror = function (error) {
    $('.status').html('<b>Status da conexão: </b>erro de conexão (' + error.message + ')');
};

// Sempre que este usuario (li) for clicado o sistema deve desmaracar os demais e em seguida marca-lo, definindo assim para quem sera enviada a mensagem
// Neste caso especifico o evento sera executado no unico "li" exitente no momento do carregamento da pagina, ou seja, o "li" "Todos"
$(".usersList").find("li").on('click', function () {
    $('.usersList').find('li').removeClass('userSelected');
    $(this).addClass('userSelected');
})        
