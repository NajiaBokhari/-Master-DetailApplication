package co.yap.master_detailapplication.networking.interfaces

import co.yap.master_detailapplication.networking.BaseRepository


interface IRepositoryHolder<T: BaseRepository> {
    val repository: T
}