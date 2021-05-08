package co.assignment.master_detailapplication.networking.interfaces

import co.assignment.master_detailapplication.networking.BaseRepository


interface IRepositoryHolder<T: BaseRepository> {
    val repository: T
}