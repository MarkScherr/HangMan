var letter;
var id;
var word;
loadWord();
var guesses = 6;
var lengthOfWord;

function loadWord(){
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/hangman',
        success: function(data) {
          id = data.id;
          word = data.word;

          lengthOfWord = word.length;
          for(var i=0 ; i<lengthOfWord ; i++){
            $('#word').append('<span id="' + i +
            '">_  <span>');
          };
        },
        error: function() {
          console.log('You need to start server!')
        }

    });
}

$('#reset').on('click', function(){
  guesses=6;
  $('.letter').show();
  $('#hangman').empty();
  $('#word').empty();
  $('#guesses').empty();
  loadWord();
  $('#guesses').text('Guesses Left: ' + guesses);

});

$('.letter').on('click', function() {

    letter = $(this).attr("value");
    $('#' + letter).hide();

    $.ajax({
        type: 'PUT',
        url: 'http://localhost:8080/hangman/' + letter + '/' + id,
        data: JSON.stringify({
            guess: letter,
            id: id

        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: 'json',
        success: function(data) {
          $.each(data, function(index, dataList) {
            if(dataList.status == 'correctGuess') {
              $('#' + dataList.locationInWord).replaceWith(letter);
              lengthOfWord--;

            } else {
              guesses--;
              drawHangman();
              $('#guesses').text('Guesses Left: ' + guesses);
            }
            if (lengthOfWord == 0) {
              $('#guesses').replaceWith("YOU WON!!!!!!");
            }
          });

        },
        error: function() {
            console.log('try again :(');
        }

    });

});

function drawHangman() {
  switch(guesses) {
    case 5:
      $('#hangman').append('<span>0</span><br>');
      break;
    case 4:
      $('#hangman').append('<span>/</span>');
      break;
    case 3:
      $('#hangman').append('<span>|</span>');
      break;
    case 2:
      $('#hangman').append('<span>\\</span><br>');
      break;
    case 1:
      $('#hangman').append('<span>/</span>');
      break;
    case 0:
      $('#hangman').append('<span>\\</span><br>');
      $('#hangman').append('YOU LOSE! <br>Word was: ' + word);

      break;

  }
}
