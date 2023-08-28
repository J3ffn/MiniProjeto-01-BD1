package br.com.dbc.walletlife.exceptions;

import java.sql.SQLException;

public class BancoDeDadosException extends SQLException {
    public BancoDeDadosException(Throwable cause) {
        super(cause);
    }

    public BancoDeDadosException(String reason) {
        super(reason);
    }
}
