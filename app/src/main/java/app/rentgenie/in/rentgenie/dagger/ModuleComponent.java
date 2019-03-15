package app.rentgenie.in.rentgenie.dagger;


import app.rentgenie.in.rentgenie.presenters.HomePresenter;

public interface ModuleComponent {
	HomePresenter getPresenter();
}
