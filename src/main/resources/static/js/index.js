const check_port_host_filename = (wssHost, wssPort, xmlFilepath) => {
    /*
        checkWssHost
        checkWssPort
        checkXmlFilepath
        POST create web-socket session
        SUBSCRIBE to web-socket heartbeat event
    */

    // return true if it is ok OR false
    const checkWssHost = (wssHost) => {
        const ipv4_host_regexp = /^((25[0-5]|(2[0-4]|1\d|[1-9]|)\d)\.?\b){4}$/;

        return wssHost.match(ipv4_host_regexp) != null;
    }

    const checkWssPort = (wssPort) => {
        const ipv4_port_regexp = /(\d){4}/;

        return wssPort.match(ipv4_port_regexp) != null;
    }

    const checkXmlFilepath = (xmlFilepath) => {
        const xml_filepath_regexp = /^.*\.xml$/

        return xmlFilepath.match(xml_filepath_regexp) != null
    }

    const wssHostIsOk = () => {
        return;
    }

    // checkWssHost
    const wssHostIsWrong = () => {
        $('#verify-host-and-port-and-xml').text("Wss host is unacceptable. Please check by pattern '0.0.0.0'.");
        $('.ui.modal').modal({blurring: true}).modal('show');

        return;
    }

    const wssHostEmptyResult = checkWssHost(wssHost) ? wssHostIsOk() : wssHostIsWrong();

    const wssPortIsOk = () => {
        return;
    }

    const wssPortIsWrong = () => {
        $('#verify-host-and-port-and-xml').text("Wss port is unacceptable. Please check by pattern '1234'.");
        $('.ui.modal').modal({blurring: true}).modal('show');

        return;
    }

    const xmlFilepathIsOk = () => {
        return;
    }

    const xmlFilepathIsWrong = () => {
        $('#verify-host-and-port-and-xml').text("Xml filepath is unacceptable. Please check filepath by pattern '*.xml'.");
        $('.ui.modal').modal({blurring: true}).modal('show');

        return;
    }

    const wssPortEmptyResult = checkWssPortHost(wssPort) ? wssPortIsOk() : wssPortIsWrong();

    const xmlFilepathEmptyResult = checkXmlFilepath(xmlFilepath) ? xmlFilepathIsOk() : xmlFilepathIsWrong();

    return;
}

$(function () {
    $("#connect-button" ).on("click", () => {
        let xmlFilepath = $('#xml-filepath').val();
        let wssPort = $('#wss-port').val();
        let wssHost = $('#wss-host').val();

        check_port_host_filename(wssHost, wssPort, xmlFilepath);
    });

    var
        $table = $('#tree-table'),
        rows = $table.find('tr');

    rows.each(function (index, row) {
        var
            $row = $(row),
            level = $row.data('level'),
            id = $row.data('id'),
            $columnName = $row.find('td[data-column="name"]'),
            children = $table.find('tr[data-parent="' + id + '"]');

        if (children.length) {
            var expander = $columnName.prepend('' +
                '<span class="treegrid-expander glyphicon glyphicon-chevron-right"></span>' +
                '');

            children.hide();

            expander.on('click', function (e) {
                var $target = $(e.target);
                if ($target.hasClass('glyphicon-chevron-right')) {
                    $target
                        .removeClass('glyphicon-chevron-right')
                        .addClass('glyphicon-chevron-down');

                    children.show();
                } else {
                    $target
                        .removeClass('glyphicon-chevron-down')
                        .addClass('glyphicon-chevron-right');

                    reverseHide($table, $row);
                }
            });
        }

        $columnName.prepend('' +
            '<span class="treegrid-indent" style="width:' + 15 * level + 'px"></span>' +
            '');
    });

    // Reverse hide all elements
    reverseHide = function (table, element) {
        var
            $element = $(element),
            id = $element.data('id'),
            children = table.find('tr[data-parent="' + id + '"]');

        if (children.length) {
            children.each(function (i, e) {
                reverseHide(table, e);
            });

            $element
                .find('.glyphicon-chevron-down')
                .removeClass('glyphicon-chevron-down')
                .addClass('glyphicon-chevron-right');

            children.hide();
        }
    };


});
