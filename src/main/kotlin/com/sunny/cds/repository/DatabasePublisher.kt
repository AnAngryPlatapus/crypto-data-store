package com.sunny.cds.repository

import com.sunny.cds.client.DatabasePublisher
import com.sunny.cds.model.book.Book
import org.springframework.stereotype.Service


@Service
class BookPublisher: DatabasePublisher<Book>()