package com.example.taskmanager.controller.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.taskmanager.R;
import com.example.taskmanager.repository.TasksRepository;
import com.example.taskmanager.model.State;
import com.example.taskmanager.model.Task;

public class AddTaskFragment extends DialogFragment {

    public static final String ARG_USERNAME = "argUsername";
    public static final String DIALOG_FRAGMENT_TAG = "Dialog";
    public static final int REQUEST_CODE_DATE_PICKER = 0;
    public static final String BUNDLE_TASK_USERNAME = "bundleTaskUsername";
    private EditText mEditTextTaskTitle;
    private EditText mEditTextDescription;
    private RadioButton mRadioButtonDone;
    private RadioButton mRadioButtonDoing;
    private RadioButton mRadioButtonTodo;
    private Button mButtonDate;
    private Button mButtonSave;
    private Button mButtonDiscard;
    private Task mTask;
    private Callbacks mCallbacks;
    private TasksRepository mTasksRepository;
    private String mUsername;

    public AddTaskFragment() {
        // Required empty public constructor
    }

    public static AddTaskFragment newInstance(long username) {
        AddTaskFragment fragment = new AddTaskFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUsername = getArguments().getString(ARG_USERNAME);
        }
        mTask = new Task(mUsername);
        mTasksRepository = TasksRepository.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_task_fragment, container, false);
        findViews(view);

        setListeners();
        return view;
    }
    private void findViews(View view) {
        mEditTextTaskTitle = view.findViewById(R.id.edit_text_task_title);
        mEditTextDescription = view.findViewById(R.id.edit_text_task_description);
        mButtonDate = view.findViewById(R.id.task_date);
        mButtonSave = view.findViewById(R.id.button_save);
        mButtonDiscard = view.findViewById(R.id.button_discard);
        mRadioButtonDone = view.findViewById(R.id.radio_button_done);
        mRadioButtonDoing = view.findViewById(R.id.radio_button_doing);
        mRadioButtonTodo = view.findViewById(R.id.radio_button_todo);
    }
    private void setTaskState(State taskState) {
        if (taskState != null) {
            switch (taskState) {
                case DONE:
                    mRadioButtonDone.setChecked(true);
                    break;
                case DOING:
                    mRadioButtonDoing.setChecked(true);
                    break;
                case TODO:
                    mRadioButtonTodo.setChecked(true);
                    break;

            }
        }
    }
    private void setListeners() {
        mButtonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(mTask.getTaskDate());
                datePickerFragment.setTargetFragment(AddTaskFragment.this, REQUEST_CODE_DATE_PICKER);
                datePickerFragment.show(getFragmentManager(), DIALOG_FRAGMENT_TAG);
            }
        });
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTask.getTaskState() == null)
                    Toast.makeText(getActivity(), "please choose Task State", Toast.LENGTH_SHORT).show();
                else {
                    mTask.setTaskTitle(mEditTextTaskTitle.getText().toString());
                    mTask.setTaskDescription((mEditTextDescription.getText().toString()));
                        mTask.setUser(mUsername);
                        mTasksRepository.addTask(mTask);
                        mCallbacks.updateTasksFragment(mTask.getTaskState(), mTask.getUsername());
                        getDialog().cancel();
//                    TasksFragment tasksFragment = (TasksFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container);
//                    tasksFragment.updateUI();

                }
            }
        });
        mButtonDiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().cancel();
            }
        });
        mRadioButtonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTask.setTaskState(State.DONE);
            }
        });
        mRadioButtonDoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTask.setTaskState(State.DOING);

            }
        });
        mRadioButtonTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTask.setTaskState(State.TODO);

            }
        });

    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(BUNDLE_TASK_USERNAME, mUsername);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof TaskDetailFragment.Callbacks)
            mCallbacks = (AddTaskFragment.Callbacks) context;
        else {
            throw new ClassCastException(context.toString()
                    + "you must Implements onTaskUpdated");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    private void updateTask() {
        mTasksRepository.update(mTask);
//        mCallbacks.onTaskUpdated();

    }

    public interface Callbacks {
        void updateTasksFragment(State taskState, String username);
    }
}