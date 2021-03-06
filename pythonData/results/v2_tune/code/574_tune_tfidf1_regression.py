import numpy as np
import pandas as pd
from sklearn.linear_model import LogisticRegression
from sklearn.svm import LinearSVC,SVC
from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import GradientBoostingClassifier, RandomForestClassifier
from sklearn.metrics import classification_report,confusion_matrix
from sklearn.naive_bayes import GaussianNB
from sklearn.neural_network import MLPClassifier
from sklearn.ensemble import AdaBoostClassifier
from sklearn.discriminant_analysis import LinearDiscriminantAnalysis
from sklearn.discriminant_analysis import QuadraticDiscriminantAnalysis
from sklearn.model_selection import cross_val_score, cross_val_predict, StratifiedKFold, train_test_split
import os
from sklearn.metrics import precision_score,accuracy_score
import matplotlib.pyplot as plt; plt.rcdefaults()
import numpy as np
import matplotlib.pyplot as plt
from sklearn.model_selection import GridSearchCV
import xgboost as xgb
from sklearn.metrics import mean_squared_error,mean_absolute_error


def createDirIfNotExist(fopOutput):
    try:
        # Create target Directory
        os.mkdir(fopOutput)
        print("Directory ", fopOutput, " Created ")
    except FileExistsError:
        print("Directory ", fopOutput, " already exists")

nameSystem='code'
fopInput='data/vector574_Tfidf1/'
fopOutput='result/tune_574_tfidf1_'+nameSystem+'_regression/'
createDirIfNotExist(fopOutput)



nameClassifier='SVC'
fpVectorItemTrainCate = fopInput+nameSystem  + '_train_regression.csv'
fpVectorItemTestCate = fopInput+nameSystem  + '_test_regression.csv'
fpResultAll=fopOutput+'summary.txt'
fpDetailsDefaultClassifier=fopOutput+'details_default.txt'
fpDetailsTunedClassifier=fopOutput+'details_tune.txt'

df_train = pd.read_csv(fpVectorItemTrainCate)
print(list(df_train.columns.values))
y_train = df_train['star']
X_train = df_train.drop(['no','star'],axis=1)

df_test = pd.read_csv(fpVectorItemTestCate)
print(list(df_test.columns.values))
y_test = df_test['star']
X_test = df_test.drop(['no','star'],axis=1)

classifier=xgb.XGBRegressor(objective ='reg:linear', colsample_bytree = 0.3, learning_rate = 0.1,
                max_depth = 5, alpha = 10, n_estimators = 10)
classifier.fit(X_train,y_train)
class_predictions =classifier.predict(X_test)
class_maeAccuracy = mean_absolute_error(y_test, class_predictions)
class_mqeAccuracy = mean_squared_error(y_test, class_predictions)
# parameter_space = {
#     'hidden_layer_sizes': [(50,50,50), (50,100,50), (100,)],
#     'activation': ['tanh', 'relu'],
#     'solver': ['sgd', 'adam'],
#     'alpha': [0.0001, 0.05],
#     'learning_rate': ['constant','adaptive'],
# }
# param_grid = {'C': [0.1,1, 10, 100], 'gamma': [1,0.1,0.01,0.001],'kernel': ['rbf', 'poly', 'sigmoid']}
parameters = {'objective':['reg:linear'],
            'colsample_bytree' : [0.3],
              'learning_rate': [0.1,1], #so called `eta` value
              'max_depth': [ 1,3,5],
              'alpha':[10,20],

              # 'min_child_weight': [4],
              # 'silent': [1],
              # 'subsample': [0.7],
              # 'colsample_bytree': [0.7],
              'n_estimators': [10,100]}

grid = GridSearchCV(classifier,parameters,scoring='neg_mean_absolute_error',refit=True,verbose=2)
grid.fit(X_train,y_train)
# grid_predictions = grid.predict(X_test)
# grid_maeAccuracy = mean_absolute_error(y_test, grid_predictions)
# grid_mqeAccuracy = mean_squared_error(y_test, grid_predictions)

best_class=grid.best_estimator_
grid_predictions = best_class.predict(X_test)
grid_maeAccuracy = mean_absolute_error(y_test, grid_predictions)
grid_mqeAccuracy = mean_squared_error(y_test, grid_predictions)

# classifier.fit(X_train,y_train)
# grid_predictions = classifier.predict(X_test)
# cross_val = cross_val_score(grid.best_estimator_, all_data, all_label, cv=kfold, n_jobs=1)
# grid_predictions =cross_val_predict(grid.best_estimator_, all_data, all_label, cv=kfold)
o2 = open(fpResultAll, 'w')
o2.write('Default estimator:\n'+str(classifier)+'\n')
o2.write('MAE: {}\nMQE: {}\n'.format(class_maeAccuracy,class_mqeAccuracy))
# o2.write(str(confusion_matrix(all_label,class_predictions))+'\n')
# o2.write(str(classification_report(all_label,class_predictions))+'\n')
# o2.write(str(accuracy_score(all_label,class_predictions))+'\n\n\n')

# print(str(grid.estimator))

o2.write('Best estimator:\n'+str(grid.estimator)+'\n')
o2.write('Result for best estimator of ' + nameClassifier + '\n')
# o2.write(str(confusion_matrix(y_test,grid_predictions))+'\n')
# o2.write(str(classification_report(y_test,grid_predictions))+'\n')
o2.write('MAE: {}\nMQE: {}\n'.format(grid_maeAccuracy,grid_mqeAccuracy))

o2.close()
np.savetxt(fpDetailsDefaultClassifier, class_predictions, fmt='%s', delimiter=',')
np.savetxt(fpDetailsTunedClassifier, grid_predictions, fmt='%s', delimiter=',')
# try:
#     filePredict = ''.join([fopOutputItemDetail, file, '_', arrClassifierName[index - 1], '.txt'])
#     print("********", "\n", "10 fold CV Results with: ", str(classifier))
#     cross_val = cross_val_score(classifier, all_data, all_label, cv=k_fold, n_jobs=1)
#     predicted = cross_val_predict(classifier, all_data, all_label, cv=k_fold)
#     weightAvg = precision_score(all_label, predicted, average='weighted') * 100
#     normalAvg = accuracy_score(all_label, predicted) * 100
#     print('{:.2f}'.format(normalAvg))
#
#     np.savetxt(filePredict, predicted, fmt='%s', delimiter=',')
#     o2 = open(fpResultAll, 'a')
#     o2.write('Result for ' + str(classifier) + '\n')
#     o2.write(str(sum(cross_val) / float(len(cross_val))) + '\n')
#     o2.write(str(confusion_matrix(all_label, predicted)) + '\n')
#     o2.write(str(classification_report(all_label, predicted)) + '\n')
#     o2.close()
#
#     strClassX = str(arrClassifierName[index - 1])
# except Exception as inst:
#     print("Error ")
#     print(type(inst))  # the exception instance
#     print(inst.args)  # arguments stored in .args
#     print(inst)
