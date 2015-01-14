package br.com.rotapublicitaria.conversor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Principal extends Activity {

    private EditText editValor;
    private Spinner spinnerUnidade;
    private TextView
            txtPolegada,
            txtPaica,
            txtPonto,
            txtMilimetro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        iniciarTextView();
        adicionarUnidadesSpinner();
        adicionarListenerSpinner();

        editValor = (EditText) findViewById(R.id.edit_valor);
    }

    private void iniciarTextView() {
        txtPolegada = (TextView) findViewById(R.id.txt_polegada);
        txtPaica = (TextView) findViewById(R.id.txt_paica);
        txtPonto = (TextView) findViewById(R.id.txt_ponto);
        txtMilimetro = (TextView) findViewById(R.id.txt_milimetro);
    }

    private void adicionarUnidadesSpinner() {

        spinnerUnidade = (Spinner) findViewById(R.id.spinner_unidade);

        ArrayAdapter<CharSequence> spinnerUnidadeAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.unidades_conversao,
                android.R.layout.simple_spinner_item
        );

        spinnerUnidadeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerUnidade.setAdapter(spinnerUnidadeAdapter);

    }

    private void adicionarListenerSpinner() {

        spinnerUnidade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position != 0) {

                    if (editValor.getText().toString().length() != 0) {

                        String itemSelecionado = parent.getItemAtPosition(position).toString();

                        editValor.setFocusable(false);

                        atualizarValores(itemSelecionado);

                    } else {

                        parent.setSelection(0);
                        editValor.setFocusable(true);
                        editValor.setError("Insira um valor");

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void atualizarValores(String itemSelecionado) {

        String s = editValor.getText().toString().trim();

        Medida medida = null;

        if(s.length() != 0) {

            double valor = Double.parseDouble(s);

            if (itemSelecionado.equals("IN")) {
                medida = new Medida(valor, Medida.Unidade.IN);

            } else if (itemSelecionado.equals("PICA")) {
                medida = new Medida(valor, Medida.Unidade.PICA);

            } else if (itemSelecionado.equals("PT")) {
                medida = new Medida(valor, Medida.Unidade.PT);

            } else if (itemSelecionado.equals("MM")) {
                medida = new Medida(valor, Medida.Unidade.MM);
            }

        }

        if(medida != null) {

            txtPolegada.setText(medida.converterPara(Medida.Unidade.IN).getValorFormatado());
            txtPaica.setText(medida.converterPara(Medida.Unidade.PICA).getValorFormatado());
            txtPonto.setText(medida.converterPara(Medida.Unidade.PT).getValorFormatado());
            txtMilimetro.setText(medida.converterPara(Medida.Unidade.MM).getValorFormatado());

        } else {

            txtPolegada.setText("-");
            txtPaica.setText("-");
            txtPonto.setText("-");
            txtMilimetro.setText("-");

        }

    }

    public void limparSpinner(View view) {

        editValor.setText("");
        spinnerUnidade.setSelection(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.sobre:
                startActivity(new Intent(this, Sobre.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
