package com.example.koinz.data

import android.content.Context
import androidx.paging.PagingSource
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class PhotosDataBaseTest {

    private lateinit var db: PhotosDataBase
    private lateinit var keysDao: RemoteKeysDao
    private lateinit var photosDao: PhotosDao


    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, PhotosDataBase::class.java).build()
        photosDao = db.photosDao()
        keysDao = db.remoteKeysDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun testPhotosInsertion() = runBlocking {
        val photosList = ArrayList<Photo>()
        photosList.add(
            Photo(
                "islam",
                "islam.com",
                "server",
                0,
                1,
                66,
                "id",
                "",
                "islamTitle",
                1
            )
        )
        photosList.add(
            Photo(
                "islam2",
                "islam.com",
                "server",
                0,
                1,
                66,
                "ids",
                "",
                "islamTitle",
                1
            )
        )
        photosDao.insertAll(photosList as List<Photo>)
        val photos = photosDao.getAllPhotos()

        val actual = photos.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        val pagingData = (actual as PagingSource.LoadResult.Page).data
        val result = pagingData.find {
            it.owner == "islam2" && it.id == "ids"
        }
        assertThat(result != null).isTrue()

    }

    @Test
    fun testRemoteKeysDataBaseInsertAndGet() = runBlocking {
        val remoteKeysList = ArrayList<PhotosRemoteKey>()
        remoteKeysList.add(PhotosRemoteKey("ids", 1, 2))
        remoteKeysList.add(PhotosRemoteKey("id", 2, 3))
        keysDao.insertRemoteKeys(remoteKeysList)

        val result = keysDao.remoteKeysPhotoId("ids")
        assertThat(result?.nextKey == 2).isTrue()
    }
}