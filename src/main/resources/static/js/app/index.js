const index = {
    init: function () {
        const _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#btn-update').on('click', function () {
            _this.update();
        });
    },
    save : function () {
        const data = {
            name: $('#name').val(),
            email: $('#email').val()
        };

        $.ajax({
            type: 'POST',
            url: '/clients',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('고객이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        const id = $('#id').val();
        const data = {
            name: $('#name').val(),
            email: $('#email').val()
        };

        $.ajax({
            type: 'PUT',
            url: '/clients/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('고객이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
          alert(JSON.stringify(error));
        });
    },
    remove : function (id) {
        $.ajax({
            type: 'DELETE',
            url: '/clients/'+id,
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('고객이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
          alert(JSON.stringify(error));
        });
    }
}

index.init();
