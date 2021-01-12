package alura.com.br.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

import alura.com.br.R;
import alura.com.br.dao.AlunoDAO;
import alura.com.br.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITLE_FORMULARIO_ALUNO = "Novo aluno";
    private final AlunoDAO dao = new AlunoDAO();
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITLE_FORMULARIO_ALUNO);

        inicializaCampos();
        configuraBotaoSalvar();

        Intent dados = getIntent();
        if(dados.hasExtra("aluno")){
            aluno = (Aluno) dados.getSerializableExtra("aluno");
            campoNome.setText(aluno.getNome());
            campoTelefone.setText(aluno.getTelefone());
            campoEmail.setText(aluno.getEmail());
        } else {
            aluno = new Aluno();
        }
    }

    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.activity_formulatio_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Aluno alunoCriado = preencheAluno();
//                salvaAluno(alunoCriado);

            preencheAluno();
            if(aluno.temIdValido()){
                dao.edita(aluno);
            } else {
                dao.salva(aluno);
            }
            finish();

//          debug
//          Toast.makeText(FormularioAlunoActivity.this, alunoCriado.getNome() + " - " +
//            alunoCriado.getTelefone() + " - " + alunoCriado.getEmail(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

//        return new Aluno(nome, telefone, email);
        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
    }

    private void inicializaCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }
}
