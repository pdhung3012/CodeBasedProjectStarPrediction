# CodeBasedProjectStarPrediction
Popularity is one of important metrics of software projects to beevaluated in large scale software repository. The star count is considered as the representation of popularity of software projects inpopular systems like Github. Knowing how many stars the project may get after a duration of time is an interesting feature that therepository can give to the developer. There are works on predicting Github project stars based on different algorithms of MachineLearning (ML) approaches. However, these works relied on features represented how developers commit to their project, which are not be able to see when the project is created. In this work, we propose an alternative scenario, which developers upload a project for first time and the ML model needs to predict the popularity (numberof stars) of that project. We relied on features built on the most important part of the project: source code and Abstract Syntax Tree. Our tool achieves 32% as accuracy with star range classification and 600 as Mean Absolute Error for exact star prediction, which shows challenges along with rooms for improvement in future works.

Presentation: https://tinyurl.com/ya4umzft

# Requirements:
- Python 3.6
- Scikit learn 0.22.2.
- Eclipse JDT

# Setup Instructions:
Note: You need to change the file path specified in the source code. To running the ML model using pretrained vectors only, go to step 3 and 4. To get the input data for step 1 as data set of Java projects, visit this repository from code2vec paper (POPL 2019) https://github.com/tech-srl/code2seq/blob/master/README.md#datasets

1. Parsing AST file:
- Run src/runOnGithubProjects/RunOnLargeScaleData.java
- Expect output data in this step is in pythonData/data/code_database.txt
2. Vectorizing textual content:
- Run pythonData/code/574_genVec_ast_p1.py
- Expected output: pythonData/data/vector574_Tfidf1
3. Machine Learning Classification:
- Run pythonData/code/574_tfidf1_allTrain_p2_cate.py
- Expected output: pythonData/results/v1/574_ast_cate
4. Machine Learning Regression:
- Run code/574_tfidf_traintest_p2_regression.py
- Expected output: pythonData/results/v1/574_ast_reg

Thank you!

