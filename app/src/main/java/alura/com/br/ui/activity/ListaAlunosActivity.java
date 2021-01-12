package alura.com.br.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import alura.com.br.R;
import alura.com.br.dao.AlunoDAO;
import alura.com.br.model.Aluno;

import static alura.com.br.ui.activity.ConstantesActivity.CHAVE_ALUNO;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITLE_LISTA_ALUNOS = "Lista de Alunos";
    final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(TITLE_LISTA_ALUNOS);
        setContentView(R.layout.activity_lista_alunos);
        configuraFAB();

        //for debug
        dao.salva(new Aluno("Alex", "1122223333", "alex@alura.com.br"));
        dao.salva(new Aluno("Fran", "1122223333", "fran@gmail.com"));
    }

    private void configuraFAB() {
        FloatingActionButton fab = findViewById(R.id.activity_main_fab_novo_aluno);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreFormularioModoInsereAluno();
            }
        });
    }

    private void abreFormularioModoInsereAluno() {
        startActivity(new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
        carregaListaDeAlunos();
    }


    private void carregaListaDeAlunos() {
        final List<Aluno> alunos = dao.todos();
        final ListView listaDeAlunos = findViewById(R.id.activity_lista_de_alunos_listview);
        configuraAdapter(alunos, listaDeAlunos);
        configuraListenerDeCliquePorItem(listaDeAlunos);
    }

    private void configuraListenerDeCliquePorItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Aluno alunoEscolhido  = alunos.get(position); // dessa forma, poderia ser passado uma lista diferente do que esta no adapter
                Aluno alunoEscolhido = (Aluno) parent.getItemAtPosition(position); // dessa forma garante que o a lista pega o aluno do adapter
//                Log.i("Lista de alunos pos", ":" + position);
//                Log.i("Lista de alunos id", ":" + alunoEscolhido .getId());

                abreFormularioParaEdicao(alunoEscolhido);
            }
        });
    }

    private void abreFormularioParaEdicao(Aluno aluno) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(vaiParaFormularioActivity);
    }

    private void configuraAdapter(List<Aluno> alunos, ListView listaDeAlunos) {
        listaDeAlunos.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos));
    }
}
