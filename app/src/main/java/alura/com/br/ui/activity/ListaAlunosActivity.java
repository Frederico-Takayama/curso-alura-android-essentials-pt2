package alura.com.br.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import alura.com.br.R;
import alura.com.br.dao.AlunoDAO;
import alura.com.br.model.Aluno;

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
                carregaActivityFormularioAluno();
            }
        });
    }

    private void carregaActivityFormularioAluno() {
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
        final List<Aluno> todos = dao.todos();
        final ListView listaDeAlunos = findViewById(R.id.activity_lista_de_alunos_listview);
        listaDeAlunos.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, todos));
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoEscolhido  = todos.get(position);
                Log.i("Lista de alunos pos", ":" + position);
                Log.i("Lista de alunos id", ":" + alunoEscolhido .getId());

                Intent vaiParaFormularioActivity  = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
                vaiParaFormularioActivity .putExtra("aluno", alunoEscolhido );
                startActivity(vaiParaFormularioActivity );
            }
        });
    }
}
